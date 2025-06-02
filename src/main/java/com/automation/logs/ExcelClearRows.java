package com.automation.logs;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelClearRows {

    public static void preExecutionSetup() throws IOException {
        String excelFilePath = System.getProperty("user.dir")+"/analysis/failedTestDetails.xlsx";
        FileInputStream fis = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // or workbook.getSheet("SheetName")

        // Delete all rows except the first (header)
        int lastRow = sheet.getLastRowNum();
        for (int i = lastRow; i > 0; i--) {
            Row row = sheet.getRow(i);
            if (row != null) {
                sheet.removeRow(row);
            }
        }

        fis.close(); // Close input stream

        // Write changes back to the file
        FileOutputStream fos = new FileOutputStream(excelFilePath);
        workbook.write(fos);
        workbook.close();
        fos.close();

        System.out.println("Rows cleared except header.");
    }
}
