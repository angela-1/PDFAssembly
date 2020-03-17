package org.openjfx.task;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;

import static com.itextpdf.kernel.pdf.PdfName.Document;

public class PageNumber extends MyTask {
    private String addPageNumber() {
        String srcFile = "d:\\d.pdf"; //GetDstFile();
        String dstFile = "d:\\c.pdf"; //GetDstFile();
        try {

            PdfDocument pdfDoc = new PdfDocument(new PdfReader(srcFile), new PdfWriter(dstFile));
            Document doc = new Document(pdfDoc);
            int totalPage = pdfDoc.getNumberOfPages();

            for (int i = 1; i <= totalPage; i++) {
                PdfPage page = doc.getPdfDocument().getPage(i);
//            Rec rec = pos.GetPos(i, page);
                PdfCanvas canvas = new PdfCanvas(page);
//            DrawWhiteBack(canvas, rec);
//            DrawNumber(canvas, i, totalPage, doc, font, rec.pointX, rec.pointY);
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
        return null;
    }
}
