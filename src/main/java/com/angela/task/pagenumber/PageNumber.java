/*
  添加页码模块
 */
package com.angela.task.pagenumber;


import com.angela.Context;
import com.angela.domain.NumberPos;
import com.angela.domain.NumberStyle;
import com.angela.task.MyTask;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PageNumber extends MyTask {


    private static final int MAX_PROGRESS = 100;

    private final String srcFile;
    private final MyStyle myStyle;
    private final MyPos myPos;


    public PageNumber(Context config) {
        List<String> source = new ArrayList<>();
        Object sourceObj = config.getContext().get("source");
        if (sourceObj instanceof List<?>) {
            for (Object o : (List<?>) sourceObj) {
                source.add((String) o);
            }
        }
        srcFile = source.get(0);
        Object configObj = config.getContext().get("config");
        Map<String, Object> configMap = new HashMap<>();
        if (configObj instanceof Map) {
            for (Object o : ((Map<?, ?>) configObj).keySet()) {
                String key = (String) o;
                configMap.put(key, ((Map<?, ?>) configObj).get(key));
            }
        }

        NumberStyle numberStyle = (NumberStyle) configMap.get("style");
        NumberPos numberPos = (NumberPos) configMap.get("pos");
        myStyle = NumberFactory.getNumberStyle(numberStyle);
        myPos = NumberFactory.getNumberPos(numberPos);
    }

    /**
     * 获取目标文件路径
     * @return 目标文件路径
     */
    private Path getDstFile() {
        Path file = Paths.get(srcFile);
        Path dir = file.getParent();
        Path filename = file.getFileName();
        String name = filename.toString().split("\\.")[0];
        return Paths.get(dir.toString(), name + "_添加页码_" + myStyle.getName()
                + "_" + myPos.getName() + ".pdf");
    }

    private void drawBack(PdfCanvas canvas, Rec rec) {
        canvas.setFillColor(ColorConstants.WHITE);
        canvas.rectangle(rec.x, rec.y, rec.width, rec.height);
        canvas.fill();
    }

    private void drawNumber(PdfCanvas canvas, int currentPage, int totalPage, Document doc,
                            PdfFont font, float x, float y) {
        canvas.setFillColor(ColorConstants.BLACK);
        doc.showTextAligned(
                new Paragraph(myStyle.getNumberString(currentPage, totalPage))
                        .setFont(font).setFontSize(18f),
                x, y, currentPage, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
    }

    private String addPageNumber() {
        updateProgress(1, MAX_PROGRESS);
        updateMessage("处理中……");
        String dstFile = getDstFile().toString();
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfReader(srcFile), new PdfWriter(dstFile));
            Document doc = new Document(pdfDoc);
            int totalPage = pdfDoc.getNumberOfPages();
            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);

            for (int i = 1; i <= totalPage; i++) {
                updateProgress(MAX_PROGRESS * i / totalPage, MAX_PROGRESS);
                PdfPage page = doc.getPdfDocument().getPage(i);
                Rec rec = myPos.getPos(i, page);
                PdfCanvas canvas = new PdfCanvas(page);
                drawBack(canvas, rec);
                drawNumber(canvas, i, totalPage, doc, font, rec.getPointX(), rec.getPointY());
            }
            doc.close();

        } catch (Exception e) {
            e.printStackTrace();
            failed();
        }
        done();
        updateProgress(MAX_PROGRESS, MAX_PROGRESS);
        updateMessage("完成");
        return dstFile;
    }

    @Override
    public String runTask() {
        return addPageNumber();
    }
}
