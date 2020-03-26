package com.angela.task.toc;

public class DocxTocTask extends TocTask {

    public DocxTocTask(String srcFile) {
        super(srcFile);
    }

    @Override
    public String generate() {
        String dstFile = getDstFile("docx");
        System.out.println("generate docx toc" + dstFile);
        parseToc();

        return "";
    }

    @Override
    public String runTask() {
        return generate();
    }
}
