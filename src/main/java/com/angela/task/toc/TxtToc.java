package com.angela.task.toc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;

public class TxtToc extends MyToc {

    private final String srcFile;

    public TxtToc(String srcFile) {
        this.srcFile = srcFile;
    }

    @Override
    protected String call() throws Exception {
        return generate(srcFile);
    }

    @Override
    public String generate(String srcFile) {
        updateProgress(1, 100);
        String dstFile = getDstFile(srcFile, "txt");
        System.out.println("generate toc" + dstFile);
        parseToc(srcFile);
        try {
            Files.deleteIfExists(Paths.get(dstFile));
            Files.createFile(Paths.get(dstFile));
            BufferedWriter writer = Files.newBufferedWriter(
                    Paths.get(dstFile), StandardCharsets.UTF_8);
            for (var line : outlines)
            {
                updateProgress(outlines.indexOf(line), outlines.size());
                writer.write(line.title + '\t' + line.page + '\n');
            }
            writer.flush();
            writer.close();
            updateProgress(100, 100);
            done();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dstFile;
    }
}
