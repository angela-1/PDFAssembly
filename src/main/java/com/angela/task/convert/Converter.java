package com.angela.task.convert;

import com.angela.Context;
import com.angela.task.MyTask;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class Converter extends MyTask {

    public Converter(Context config) {
        System.out.println("convert constructor " + config.toString());
    }

    private String convertToPdf() throws IOException {
        String docPath = "d:\\word file.docx";
        String pdfPath = "d:\\WordDocument.pdf";

        InputStream in = new FileInputStream(new File(docPath));
        XWPFDocument document = new XWPFDocument(in);
        PdfOptions options = PdfOptions.create();
        OutputStream out = new FileOutputStream(new File(pdfPath));
        PdfConverter.getInstance().convert(document, out, options);

        document.close();
        out.close();

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
