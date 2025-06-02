package com.automation.session;
import com.automation.testreport.TestReport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

public class SessionInfoRetriever {

    private static final String INPUT_EXCEL = System.getProperty("user.dir")+"/src/main/java/com/automation/session/sessionIds.xlsx";  // Path to your Excel

    public static void getSessionInfoForFailedTest(TestReport testReport) {

       String testId = testReport.getTestId();

        try (FileInputStream fis = new FileInputStream(INPUT_EXCEL);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Column indices
            int testIdCol = -1;
            int sessionIdCol = -1;
            int timestampCol = -1;

            // Get header row
            Row headerRow = sheet.getRow(0);
            for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
                String header = headerRow.getCell(i).getStringCellValue().trim();
                if (header.equalsIgnoreCase("Test_Id")) testIdCol = i;
                else if (header.equalsIgnoreCase("sessionId")) sessionIdCol = i;
                else if (header.equalsIgnoreCase("timestamp")) timestampCol = i;
            }

            if (testIdCol == -1 || sessionIdCol == -1 || timestampCol == -1) {
                System.out.println("Required columns not found.");
                return;
            }

            boolean found = false;

            // Iterate over rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell testIdCell = row.getCell(testIdCol);
                if (testIdCell != null && testIdCell.getCellType() == CellType.STRING &&
                        testIdCell.getStringCellValue().trim().equalsIgnoreCase(testId)) {

                    String sessionId = row.getCell(sessionIdCol).getStringCellValue().trim();
                    String timestamp = row.getCell(timestampCol).toString().trim();
                    testReport.setSessionId(sessionId);
                    testReport.setTimestamp(timestamp);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No matching TestId found for: " + testId);
            }

        } catch (Exception e) {
            System.err.println("Error reading Excel: " + e.getMessage());
        }
    }



}
