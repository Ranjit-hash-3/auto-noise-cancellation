package com.automation.logs;

import com.automation.testreport.TestReport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ServerLogAnalyzer {

    private static final String INPUT_EXCEL = System.getProperty("user.dir")+"/serverlogs/server_error_log.xlsx";

    public static void fetchErrorDetailsBySession(TestReport testReport,String sessionIdInput,String timestampInput) {
        try (FileInputStream fis = new FileInputStream(INPUT_EXCEL);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Map<String, Integer> columnMap = new HashMap<>();
            Row header = sheet.getRow(0);

            for (int i = 0; i < header.getLastCellNum(); i++) {
                columnMap.put(header.getCell(i).getStringCellValue().trim(), i);
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String sessionId = row.getCell(columnMap.get("sessionId")).getStringCellValue().trim();
                String timestamp = row.getCell(columnMap.get("timestamp")).toString().trim();

                if (sessionId.equals(sessionIdInput) && timestamp.equals(timestampInput)) {
                    String errorCode = row.getCell(columnMap.get("errorCode")).getStringCellValue();
                    String errorMessage = row.getCell(columnMap.get("errorMessage")).getStringCellValue();
                    String exceptionMessage = row.getCell(columnMap.get("exceptionMessage")).getStringCellValue();
                    testReport.setErrorCode(errorCode);
                    testReport.setErrorMessage(errorMessage);
                    testReport.setExceptionMessage(exceptionMessage);

                    break;
                }
            }




        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
        }
    }



}
