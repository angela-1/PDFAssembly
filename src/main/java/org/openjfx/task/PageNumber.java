package org.openjfx.task;


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


public class PageNumber extends MyTask {

    private void DrawNumber(PdfCanvas canvas, int currentPage, int totalPage, Document doc,
                            PdfFont font, float x, float y) {
        canvas.setFillColor(ColorConstants.BLACK);
        doc.showTextAligned(
                new Paragraph("3266")
                        .setFont(font).setFontSize(18f),
                x, y, currentPage, TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0);
    }

    private String addPageNumber() {
        String srcFile = "d:\\d.pdf"; //GetDstFile();
        String dstFile = "d:\\c.pdf"; //GetDstFile();
        try {

            PdfDocument pdfDoc = new PdfDocument(new PdfReader(srcFile), new PdfWriter(dstFile));
            Document doc = new Document(pdfDoc);
            int totalPage = pdfDoc.getNumberOfPages();
            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);

            for (int i = 1; i <= totalPage; i++) {
                PdfPage page = doc.getPdfDocument().getPage(i);
//            Rec rec = pos.GetPos(i, page);
                PdfCanvas canvas = new PdfCanvas(page);
//            DrawWhiteBack(canvas, rec);
            DrawNumber(canvas, i, totalPage, doc, font, 200, 200);
            }
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dstFile;
    }

    @Override
    public String runTask() {
        System.out.println("page number task run");
        addPageNumber();
        System.out.println("task done");
        d

        return null;
    }
}
