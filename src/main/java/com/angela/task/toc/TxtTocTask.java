package com.angela.task.toc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtTocTask extends TocTask {

    public TxtTocTask(String srcFile) {
        super(srcFile);
    }

    @Override
    public String generate() {
        updateProgress(1, MAX_PROGRESS);
        String dstFile = getDstFile("txt");
        System.out.println("generate toc" + dstFile);
        parseToc();
        try {
            Files.deleteIfExists(Paths.get(dstFile));
            Files.createFile(Paths.get(dstFile));
            BufferedWriter writer = Files.newBufferedWriter(
                    Paths.get(dstFile), StandardCharsets.UTF_8);
            for (var line : outlines) {
                updateProgress(outlines.indexOf(line), outlines.size());
                writer.write(line.title + '\t' + line.page + '\n');
            }
            writer.flush();
            writer.close();
            updateProgress(MAX_PROGRESS, MAX_PROGRESS);
            done();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dstFile;
    }

    @Override
    public String runTask() {
        return generate();
    }
}
