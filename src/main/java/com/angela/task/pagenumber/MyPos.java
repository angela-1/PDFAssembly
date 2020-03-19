package com.angela.task.pagenumber;

import com.itextpdf.kernel.pdf.PdfPage;

public interface MyPos {
    public String getName();
    public Rec getPos(int currentPage, PdfPage pdfPage);
}
