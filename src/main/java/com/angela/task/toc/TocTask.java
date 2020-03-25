package com.angela.task.toc;

import com.angela.task.MyTask;
import com.itextpdf.kernel.pdf.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class TocTask extends MyTask {

    public static class OutlineItem {
        public String title;
        public int page;
    }


    private final String srcFile;
    protected List<OutlineItem> outlines;

    public TocTask(String srcFile) {
        this.srcFile = srcFile;
        this.outlines = null;
    }

    protected void parseToc() {
        outlines = new ArrayList<OutlineItem>();
        PdfDocument pdfDoc = null;
        try {
            pdfDoc = new PdfDocument(new PdfReader(srcFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PdfOutline pdfOutline = null;
        if (pdfDoc != null) {
            pdfOutline = pdfDoc.getOutlines(false);
            PdfNameTree destsTree = pdfDoc.getCatalog().getNameTree(PdfName.Dests);

            getBookmark(pdfOutline, destsTree.getNames(), pdfDoc);
            pdfDoc.close();
        }
    }

    protected void getBookmark(PdfOutline outline, Map<String, PdfObject> names, PdfDocument pdfDoc) {

        if (outline.getDestination() != null) {
            OutlineItem outlineItem = new OutlineItem();
            outlineItem.title = outline.getTitle();
            PdfObject pageNumber = outline.getDestination()
                    .getDestinationPage(names);
            outlineItem.page = pdfDoc.getPageNumber(
                    pdfDoc.getPage((PdfDictionary) pageNumber));
            outlines.add(outlineItem);
        }

        for (var child : outline.getAllChildren()) {
            getBookmark(child, names, pdfDoc);
        }
    }

    protected Path getTocName(String srcFile, String ext) {
        Path file = Paths.get(srcFile);
        Path dir = file.getParent();
        Path filename = file.getFileName();
        String name = filename.toString().split("\\.")[0];
        return Paths.get(dir.toString(), name + "_目录." + ext);
    }

    public String getDstFile(String extension) {
        return getTocName(srcFile, extension).toString();
    }

    public abstract String generate();

}
