package org.openjfx.task.pagenumber;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

public class SidePos implements MyPos {
    @Override
    public String getName() {
        return "页边";
    }

    @Override
    public Rec getPos(int currentPage, PdfPage pdfPage) {
        Rectangle pageRec = pdfPage.getPageSizeWithRotation();
        float pageWidth = pageRec.getWidth();
        float pageHeight = pageRec.getHeight();

        float pointX;
        float pointY = pageHeight / 2;

        if (currentPage % 2 == 0)
        {
            pointX = pageWidth * 0.05f;
        }
        else
        {
            pointX = pageWidth * 0.95f;
        }

        float whiteWidth = 50f;
        float whiteHeight = 30f;
        float whiteX = pointX - whiteWidth / 2;
        float whiteY = pointY - whiteHeight / 2;

        return new Rec(whiteX, whiteY, whiteWidth, whiteHeight);
    }
}
