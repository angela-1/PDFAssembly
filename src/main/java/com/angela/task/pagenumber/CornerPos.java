package com.angela.task.pagenumber;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfPage;

public class CornerPos implements MyPos {
    @Override
    public String getName() {
        return "奇偶页左右";
    }

    @Override
    public Rec getPos(int currentPage, PdfPage pdfPage) {
        Rectangle pageRec = pdfPage.getPageSizeWithRotation();
        float pageWidth = pageRec.getWidth();

        float pointX;
        float pointY = 30.0f;

        if (currentPage % 2 == 0)
        {
            pointX = pageWidth * 0.1f;
        }
        else
        {
            pointX = pageWidth * 0.9f;
        }

        float whiteWidth = 80f;
        float whiteHeight = 30f;
        float whiteX = pointX - whiteWidth / 2;
        float whiteY = pointY - whiteHeight / 2;

        return new Rec(whiteX, whiteY, whiteWidth, whiteHeight);
    }
}
