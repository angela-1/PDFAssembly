package com.angela.task.convert;

import com.angela.Context;
import com.angela.task.MyTask;

import java.io.IOException;

public class Converter extends MyTask {

    public Converter(Context config) {
        System.out.println("convert constructor " + config.toString());
    }

    private String convertToPdf() throws IOException {
        String docPath = "d:\\word file.docx";
        String pdfPath = "d:\\WordDocument.pdf";


        return pdfPath;
    }

    @Override
    public String runTask() {
        try {
            return convertToPdf();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
