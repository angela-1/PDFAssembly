package com.angela.task.toc;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XlsxTocTask extends TocTask {

    public XlsxTocTask(String srcFile) {
        super(srcFile);
    }

    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<>();
        CellStyle style;

        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 24);
        titleFont.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(headerFont);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        styles.put("header", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        styles.put("cell", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setWrapText(true);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        styles.put("normal", style);

        return styles;
    }

    private String generateReport(String dstFile, List<OutlineItem> outlines) {
        updateProgress(1, MAX_PROGRESS);
        try (Workbook wb = new XSSFWorkbook()) { // or new HSSFWorkbook();
            Map<String, CellStyle> styles = createStyles(wb);
            /*
             * It's possible to set up repeating rows and columns in your printouts by using
             * the setRepeatingRowsAndColumns() function in the Workbook object.
             *
             * This function Contains 5 parameters: The first parameter is the index to the
             * sheet (0 = first sheet). The second and third parameters specify the range
             * for the columns to repreat. To stop the columns from repeating pass in -1 as
             * the start and end column. The fourth and fifth parameters specify the range
             * for the rows to repeat. To stop the columns from repeating pass in -1 as the
             * start and end rows.
             */
            Sheet sheet1 = wb.createSheet("Sheet1");

            XSSFPrintSetup printSetup = (XSSFPrintSetup) sheet1.getPrintSetup();
            printSetup.setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);

            // Set the columns to repeat from column 0 to 2 on the first sheet
            Row row1 = sheet1.createRow(0);
            String title = Paths.get(dstFile).getFileName().toString();
            row1.createCell(0).setCellValue(title);
            row1.getCell(0).setCellStyle(styles.get("title"));

            CellRangeAddress cra = CellRangeAddress.valueOf("A1:C1");
            sheet1.addMergedRegion(cra);


            final String[] titles = {"序号", "资料名称", "页码"};

            Row headerRow = sheet1.createRow(2);
            Cell headerCell;
            for (int i = 0; i < titles.length; i++) {
                headerCell = headerRow.createCell(i);
                headerCell.setCellValue(titles[i]);
                headerCell.setCellStyle(styles.get("header"));
            }

            int contentRowNum = 3;
            int linesNum = outlines.size();
            for (int i = 0; i < linesNum; i++) {
                System.out.println("iss " + i + "/" + linesNum);
                updateProgress(i, linesNum);

                OutlineItem item = outlines.get(i);
                Row row = sheet1.createRow(contentRowNum++);
                row.createCell(0).setCellValue(i + 1);
                row.getCell(0).setCellStyle(styles.get("cell"));
                row.createCell(1).setCellValue(item.title);
                row.getCell(1).setCellStyle(styles.get("normal"));
                row.createCell(2).setCellValue(item.page);
                row.getCell(2).setCellStyle(styles.get("cell"));
            }

            sheet1.setColumnWidth(0, 10 * 256);
            sheet1.setColumnWidth(1, 60 * 256);
            sheet1.setColumnWidth(2, 10 * 256);


            // Set the columns to repeat from column 0 to 2 on the first sheet
            // sheet1.setRepeatingColumns(CellRangeAddress.valueOf("A:C"));
            // Set the the repeating rows and columns on the second sheet.
            CellRangeAddress rep = CellRangeAddress.valueOf("A3:C3");
            // sheet1.setRepeatingColumns(rep);
            sheet1.setRepeatingRows(rep);

            // set the print area for the first sheet
            // wb.setPrintArea(0, 1, 2, 0, 3);

            // String reportPath = "xssf-printsetup.xlsx";
            // String reportPath =
            // LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +
            // ".xlsx";

            System.out.println("dst path " + dstFile.toString());

            try (FileOutputStream fileOut = new FileOutputStream(dstFile.toString())) {
                wb.write(fileOut);
                fileOut.close();
                updateProgress(MAX_PROGRESS, MAX_PROGRESS);
                done();

                return dstFile;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String generate() {
        String dstFile = getDstFile("xlsx");
        System.out.println("generate xlsx toc" + dstFile);
        parseToc();
        return generateReport(dstFile, outlines);
    }

    @Override
    public String runTask() {
        return generate();
    }
}
