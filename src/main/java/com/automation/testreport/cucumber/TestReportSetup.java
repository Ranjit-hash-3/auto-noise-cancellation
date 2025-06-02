package com.automation.testreport.cucumber;

import com.automation.testreport.TestReport;
import com.automation.testreport.FrameworkUtil;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.automation.logs.ServerLogAnalyzer.fetchErrorDetailsBySession;
import static com.automation.session.SessionInfoRetriever.getSessionInfoForFailedTest;

public class TestReportSetup {
    private static final String OUTPUT_EXCEL = System.getProperty("user.dir")+"/analysis/failedTestDetails.xlsx";
    @After(order=2)
    public static void sendReport(Scenario scenario) throws IOException {

        if(!scenario.getStatus().toString().equalsIgnoreCase("PASSED")) {

            TestReport testReport = new TestReport();
            testReport.setTestId(FrameworkUtil.getTestId(scenario));
            testReport.setEnviromentDetails(FrameworkUtil.getEnvironmentDetails());
            getSessionInfoForFailedTest(testReport); // gets SessionId,TimeStamp for given TC and EnvironmentDetails
            fetchErrorDetailsBySession(testReport, testReport.getSessionId(), testReport.getTimestamp());// for SessionID and timestamp , we will get error details(errorcode,errormsg,exception) from serverlogs
            System.out.println(testReport.toString());
            List<String[]> matchedData = new ArrayList<>();

            matchedData.add(new String[]{testReport.getTestId(), testReport.getEnviromentDetails(),
                    testReport.getSessionId(), testReport.getTimestamp(), testReport.getErrorCode(),
                    testReport.getErrorMessage(), testReport.getExceptionMessage()
            });
            writeToExcel(matchedData);
        }
    }

    private static void writeToExcel(List<String[]> matchedData) throws IOException {
        Workbook workbook;
        Sheet sheet;
        File file = new File(OUTPUT_EXCEL);
        boolean fileExists = file.exists();

        if (fileExists) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
            }
            sheet = workbook.getSheetAt(0);
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("ErrorDetails");
        }

        int rowNum = sheet.getLastRowNum() + 1;

        // Add headers only if file was just created (i.e., no header row)
        if (!fileExists || (sheet.getRow(0) == null || sheet.getRow(0).getCell(0) == null)) {
            Row headerRow = sheet.createRow(0);
            String[] headers = {"TestId", "environment","sessionId", "timestamp", "errorCode", "errorMessage", "exceptionMessage"};
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }
            rowNum = 1; // First row after header
        }

        for (String[] rowData : matchedData) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < rowData.length; i++) {
                row.createCell(i).setCellValue(rowData[i]);
            }
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        }
        workbook.close();
    }

}
