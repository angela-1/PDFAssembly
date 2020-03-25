package com.angela.task.toc;

public class TocTaskFactory {

    public static TocTask getFormatTask(String srcFile, TocFormat format) {
        TocTask tocFormat = null;
        switch (format) {
            case Xlsx:
                tocFormat = new XlsxTocTask(srcFile);
                break;
            case Docx:
                break;
            case Txt:
                tocFormat = new TxtTocTask(srcFile);
                break;
            default:
                tocFormat = new XlsxTocTask(srcFile);

        }
        return tocFormat;
    }
}
