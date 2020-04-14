package com.angela.task.merge;

import com.angela.Context;
import com.angela.Utils;
import com.angela.task.MyTask;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.kernel.pdf.navigation.PdfStringDestination;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class MergeTask extends MyTask {

    private final List<String> source;
    private final boolean keepBookmark;
    private final boolean addWhitePage;

    public MergeTask(Context config) {
        System.out.println("merger constructor " + config.toString());
        source = new ArrayList<>();
        Object sourceObj = config.getContext().get("source");
        if (sourceObj instanceof List<?>) {
            for (Object o : (List<?>) sourceObj) {
                source.add((String) o);
            }
        }
        Object configObj = config.getContext().get("config");
        Map<String, Object> configMap = new HashMap<>();
        if (configObj instanceof Map) {
            for (Object o : ((Map<?, ?>) configObj).keySet()) {
                String key = (String) o;
                configMap.put(key, ((Map<?, ?>) configObj).get(key));
            }
        }
        keepBookmark = (boolean) configMap.get("keepBookmarkProp");
        addWhitePage = (boolean) configMap.get("addWhitePageProp");

        System.out.println("this" + source + keepBookmark + addWhitePage);

    }

    private static String convertImgToPdf(String path) {
        if (!Files.exists(Paths.get(path))) {
            return null;
        }
        String dest = getPdfFile(path);
        PdfDocument pdfDoc;
        try {
            pdfDoc = new PdfDocument(new PdfWriter(dest));
            Image image = new Image(ImageDataFactory.create(path));
            pdfDoc.addNewPage(PageSize.A4);
            Document doc = new Document(pdfDoc, PageSize.A4);
            doc.add(image);
            doc.close();
        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        return dest;
    }

    private static void clean(List<String> files) {
        for (var item : files) {
            try {
                Files.deleteIfExists(Paths.get(item));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getFileTitle(String file) {
        String filePath = Paths.get(file).getFileName().toString();
        int point = filePath.lastIndexOf(".");
        return filePath.substring(0, point);
    }

    private void mergePdfs(List<String> inputFiles, String outputFile) {
        PdfDocument pdfDoc;
        try {
            pdfDoc = new PdfDocument(new PdfWriter(outputFile));
            pdfDoc.initializeOutlines();
            PdfOutline rootOutline = pdfDoc.getOutlines(true);
            String parentTitle = getFileTitle(outputFile);
            PdfOutline parent = rootOutline.addOutline(parentTitle);

            int pageNumber = 0;

            System.out.println("merge pdfs" + inputFiles);
            for (var srcFile : inputFiles) {
                String title = getFileTitle(srcFile);

                PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(srcFile));

                int pageCount = firstSourcePdf.getNumberOfPages();

                for (int i = 1; i < pageCount + 1; i++) {
//                    var page = firstSourcePdf.getPage(i);
//                    int pageRotate = page.getRotation();
//                    if (pageRotate != 0) {
//                        page.setRotation(0);
//                    }
                    PdfPage newPage = firstSourcePdf.getPage(i).copyTo(pdfDoc);
                    pdfDoc.addPage(newPage);
                }

                PdfExplicitDestination dd = PdfExplicitDestination.
                        createFit(pdfDoc.getPage(pageNumber + 1));
                String tt = UUID.randomUUID().toString();
                pdfDoc.addNamedDestination(tt, dd.getPdfObject());

                PdfOutline kid = parent.addOutline(title);
                kid.addAction(PdfAction.createGoTo(new PdfStringDestination(tt)));

                pageNumber += pageCount;
                firstSourcePdf.close();

                if (addWhitePage) {
                    if (pageCount % 2 == 1) {
                        pdfDoc.addNewPage(PageSize.A4);
                        pageNumber += 1;
                    }
                }
            }

            PdfExplicitDestination destToPage3 = PdfExplicitDestination.
                    createFit(pdfDoc.getFirstPage());
            String stringDest = UUID.randomUUID().toString();
            pdfDoc.addNamedDestination(stringDest, destToPage3.getPdfObject());
            parent.addAction(PdfAction.createGoTo(new PdfStringDestination(stringDest)));

            if (pdfDoc.getNumberOfPages() % 2 == 1) {
                pdfDoc.addNewPage(pageNumber, PageSize.A4);
            }

            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void mergePdfsWithBookmark(List<String> inputFiles, String outputFile) {
        PdfDocument pdfDoc;
        try {
            pdfDoc = new PdfDocument(new PdfWriter(outputFile));
            pdfDoc.initializeOutlines();

            PdfMerger merger = new PdfMerger(pdfDoc, true, true);
            //
//            PdfOutline rootOutline = pdfDoc.getOutlines(true);
//            String parentTitle = getFileTitle(outputFile);
//            PdfOutline parent = rootOutline.addOutline(parentTitle);

//            int pageNumber = 0;

            System.out.println("merge pdfs with bookmarks");

            for (var srcFile : inputFiles) {
                PdfDocument firstSourcePdf = new PdfDocument(new PdfReader(srcFile));
                int pageCount = firstSourcePdf.getNumberOfPages();
                merger.merge(firstSourcePdf, 1, pageCount);
                firstSourcePdf.close();

                if (addWhitePage) {
                    if (pageCount % 2 == 1) {
                        pdfDoc.addNewPage(PageSize.A4);
                    }
                }
            }

            PdfOutline rootOutline = pdfDoc.getOutlines(false);

            List<PdfOutline> listItem = new ArrayList<>(rootOutline.getAllChildren());

            rootOutline.getAllChildren().clear();

            String parentTitle = getFileTitle(outputFile);
            PdfOutline parent = rootOutline.addOutline(parentTitle);

            for (var item : listItem) {
                parent.addOutline(item);
            }

            PdfExplicitDestination destToPage3 = PdfExplicitDestination.createFit(pdfDoc.getFirstPage());
            String stringDest = UUID.randomUUID().toString();
            pdfDoc.addNamedDestination(stringDest, destToPage3.getPdfObject());
            parent.addAction(PdfAction.createGoTo(new PdfStringDestination(stringDest)));

            if (pdfDoc.getNumberOfPages() % 2 == 1) {
                pdfDoc.addNewPage(PageSize.A4);
            }

            pdfDoc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String mergeFiles(List<String> files) {
        // 已经过滤完后的文件
        System.out.println("to merge files" + files);
        if (files.size() == 0) {
            return null;
        }
        List<String> tmpFiles = new ArrayList<>();
        String dstFile = getDstFile(files.get(0));
        System.out.println("dst file" + dstFile);

        List<String> convertedFiles = files.stream().map(v -> {
            String res;
            if (v.endsWith("pdf")) {
                System.out.println("pdf" + v);
                res = v;
            } else {
                System.out.println("pic" + v);

                res = convertImgToPdf(v);
                tmpFiles.add(res);
            }
            return res;
        }).collect(Collectors.toList());


        if (keepBookmark) {
            mergePdfsWithBookmark(convertedFiles, dstFile);
        } else {
            mergePdfs(convertedFiles, dstFile);
        }

        System.out.println("tmpfiles" + tmpFiles.toString());
        clean(tmpFiles);
        return dstFile;
    }

    private static String getDstFile(String srcFile) {
        Path parent = Paths.get(srcFile).getParent();
        return parent.toString() + ".pdf";
    }

    private static String getPdfFile(String srcFile) {
        Path parent = Paths.get(srcFile).getParent();
        Path fileName = Paths.get(srcFile).getFileName();
        String name = fileName.toString().split("\\.")[0] + ".pdf";
        Path result = Paths.get(parent.toString(), name);
        return result.toString();
    }

    private List<String> getFiles(String path) {
        List<String> files = null;
        Path dir = Paths.get(path);
        try {
            files = Files.list(dir)
                    .filter(v -> Files.isRegularFile(v))
                    .map(Path::toString)
                    .collect(Collectors.toList());
            files = Utils.filter(files);
            System.out.println("sha" + files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    private String merge() {
        List<String> files;
        String result = null;
        boolean allDirectory = source.stream().allMatch(v -> Files.isDirectory(Paths.get(v)));
        if (allDirectory) {
            for (var s : source) {
                List<String> dirFiles = getFiles(s);
                System.out.println("is dir" + dirFiles);
                result = mergeFiles(dirFiles);
            }
        } else {
            files = source.stream().filter(v -> Files.isRegularFile(Paths.get(v))).collect(Collectors.toList());
            result = mergeFiles(files);
        }
        System.out.println("result" + result);
        return result;
    }

    @Override
    public String runTask() {
        return merge();
    }
}
