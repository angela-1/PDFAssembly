package org.openjfx.task;


public class PageNumber extends MyTask {
    private String addPageNumber()
    {
        String dstFile = "d:\\c.pdf"; //GetDstFile();
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(srcFile), new PdfWriter(dstFile));
//        Document doc = new Document(pdfDoc);
//        int totalPage = pdfDoc.GetNumberOfPages();
//
//        for (int i = 1; i <= totalPage; i++)
//        {
//            PdfPage page = doc.GetPdfDocument().GetPage(i);
//            Rec rec = pos.GetPos(i, page);
//            PdfCanvas canvas = new PdfCanvas(page);
//            DrawWhiteBack(canvas, rec);
//            DrawNumber(canvas, i, totalPage, doc, font, rec.pointX, rec.pointY);
//        }
//        doc.Close();
        return dstFile;
    }
    @Override
    public String runTask() {
        System.out.println("page number task run");

        return null;
    }
}
