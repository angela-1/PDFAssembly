package com.angela.task.merge;

import com.angela.Context;
import com.angela.task.MyTask;

import java.io.IOException;

public class Merger extends MyTask {

    public Merger(Context config) {
        System.out.println("merger constructor " + config.toString());
    }

    private String merge() {
        String docPath = "d:\\word file.docx";
        String pdfPath = "d:\\WordDocument.pdf";
        updateProgress(1, MAX_PROGRESS);
        updateMessage("nnimei……");

        try {
            final int max = 100;

            for (int i = 1; i <= max; i++) {
                Thread.sleep(50);
                updateProgress(i, MAX_PROGRESS);
                if (isCancelled()) {
                    break;
                }
                System.out.println("mnimeis" + i);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return pdfPath;
    }

    @Override
    public String runTask() {
        return merge();
    }
}
