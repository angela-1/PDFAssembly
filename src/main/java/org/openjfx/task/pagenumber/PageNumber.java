package org.openjfx.task.pagenumber;


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
import org.openjfx.ContextConfig;
import org.openjfx.domain.NumberPos;
import org.openjfx.domain.NumberStyle;
import org.openjfx.task.MyTask;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class PageNumber extends MyTask {

    private static final int MAX_PROGRESS = 100;

    private final String srcFile;
    private final MyStyle myStyle;
    private final MyPos myPos;


    public PageNumber(ContextConfig config) {
        List<String> source = (List<String>) config.get().get("source");
        srcFile = source.get(0);
        Map<String, Object> configMap = (Map<String, Object>) config.get().get("config");
        NumberStyle numberStyle = (NumberStyle) configMap.get("style");
        NumberPos numberPos = (NumberPos) configMap.get("pos");
        myStyle = getNumberStyle(numberStyle);
        myPos = getNumberPos(numberPos);
    }

    private Path getDstFile() {
        Path file = Paths.get(srcFile);
        Path dir = file.getParent();
        Path filename = file.getFileName();
        return Paths.get(dir.toString(), filename + "_" + myStyle.getName()
                + "_" + myPos.getName() + "_添加页码.pdf");
    }

    private MyStyle getNumberStyle(NumberStyle numberStyle) {
        MyStyle style = null;
        switch (numberStyle) {
            case Normal:
                style = new NormalStyle();
                break;
            case Collection:
                style = new CollectionStyle();
                break;
            case Total:
                style = new TotalStyle();
                break;
            case Decorator:
                style = new DecoratorStyle();
                break;
            default:
                style = new NormalStyle();
        }
        return style;
    }

    private MyPos getNumberPos(NumberPos numberPosu) {
        MyPos pos = null;
        switch (numberPosu) {
            case Center:
                pos = new CenterPos();
                break;
            case Corner:
                pos = new CornerPos();
                break;
            case Side:
                pos = new SidePos();
                break;
            default:
                pos = new CenterPos();
        }
        return pos;
    }

    private void drawBack(PdfCanvas canvas, Rec rec) {
//        canvas.setFillColor(ColorConstants.YELLOW);

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
        }
        updateProgress(MAX_PROGRESS, MAX_PROGRESS);
        updateMessage("完成");
        return dstFile;
    }

    @Override
    public String runTask() {
        return addPageNumber();
    }
}
