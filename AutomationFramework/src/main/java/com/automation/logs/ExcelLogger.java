package com.automation.logs;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelLogger {

    private static final String[] HEADERS = {
            "TestId", "errorCode", "errorMessage", "logEntry"
    };

    public void logResult(String filePath, TestResult result) throws IOException {

        File file = new File(filePath);
        Workbook workbook;
        Sheet sheet;

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Results");
            createHeaderRow(sheet);
        }

        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(result.getTestId());
        row.createCell(1).setCellValue(result.getErrorCode());
        row.createCell(2).setCellValue(result.getErrorMessage());
        row.createCell(3).setCellValue(result.getLogEntry());

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        workbook.close();

    }

    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < HEADERS.length; i++) {
            headerRow.createCell(i).setCellValue(HEADERS[i]);
        }
    }
}
