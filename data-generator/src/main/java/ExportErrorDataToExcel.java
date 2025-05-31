import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportErrorDataToExcel {
    //List<ErrorTestData> errorTestDataList
    //ErrorTestData errorTestData
    public static void exportDataToExcel(List<ErrorTestData> errorTestDataList) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Error Test Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Error Code");
            headerRow.createCell(1).setCellValue("Error Message");
            headerRow.createCell(2).setCellValue("Timestamp");
            headerRow.createCell(3).setCellValue("User ID");
            headerRow.createCell(4).setCellValue("Device Type");
            headerRow.createCell(5).setCellValue("App Version");
            headerRow.createCell(6).setCellValue("Log Entry");
            headerRow.createCell(7).setCellValue("RCA");
            headerRow.createCell(8).setCellValue("Mitigation");
            int rowNum = 1;
            for (ErrorTestData errorTestData : errorTestDataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(errorTestData.getErrorcode());
                row.createCell(1).setCellValue(errorTestData.getErrorMessage());
                row.createCell(2).setCellValue(errorTestData.getTimestamp());
                row.createCell(3).setCellValue(errorTestData.getUserId());
                row.createCell(4).setCellValue(errorTestData.getDeviceType());
                row.createCell(5).setCellValue(errorTestData.getAppVersion());
                row.createCell(6).setCellValue(errorTestData.getLogEntry());
                row.createCell(7).setCellValue(errorTestData.getRca());
                row.createCell(8).setCellValue(errorTestData.getMitigation());
                }
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sheet.autoSizeColumn(i);
            }
            try (FileOutputStream fileOut = new FileOutputStream("error_test_data.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Test Data Generated successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

