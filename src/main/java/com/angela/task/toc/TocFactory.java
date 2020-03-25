package com.angela.task.toc;

import javafx.concurrent.Task;

public class TocFactory {

    public static MyToc getTocFormat(String srcFile, TocFormat format) {
        MyToc tocFormat = null;
        switch (format) {
            case Xlsx:
                tocFormat = new XlsxToc(srcFile);
                break;
            case Docx:
                break;
            case Txt:
                tocFormat = new TxtToc(srcFile);
                break;
            default:
                tocFormat = new XlsxToc(srcFile);

        }
        return tocFormat;
    }
}
