package org.openjfx.task.pagenumber;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

public class CenterPos implements MyPos {
    @Override
    public String getName() {
        return "居中";
    }

    @Override
    public Rec getPos(int currentPage, PdfPage pdfPage) {
        Rectangle pageRec = pdfPage.getPageSizeWithRotation();
        float pageWidth = pageRec.getWidth();

        float pointX = pageWidth / 2;
        float pointY = 30.0f;

        float whiteWidth = 80f;
        float whiteHeight = 30f;
        float whiteX = pointX - whiteWidth / 2;
        float whiteY = pointY - whiteHeight / 2;

        return new Rec(whiteX, whiteY, whiteWidth, whiteHeight);
    }
}
