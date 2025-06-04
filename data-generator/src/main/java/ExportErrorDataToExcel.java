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
    //errorCode	exceptionMessage	errorMessage	RCA	Mitigation	timestamp	userId	deviceType	appVersion	sessionId	module	username
    public static void exportDataToExcel(List<ErrorTestData> errorTestDataList) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Error Test Data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("errorCode");
            headerRow.createCell(1).setCellValue("exceptionMessage");
            headerRow.createCell(2).setCellValue("errorMessage");
            headerRow.createCell(3).setCellValue("RCA");
            headerRow.createCell(4).setCellValue("Mitigation");
            headerRow.createCell(5).setCellValue("timestamp");
            headerRow.createCell(6).setCellValue("userId");
            headerRow.createCell(7).setCellValue("deviceType");
            headerRow.createCell(8).setCellValue("appVersion");
            headerRow.createCell(9).setCellValue("sessionId");
            headerRow.createCell(10).setCellValue("module");
            headerRow.createCell(11).setCellValue("username");


            int rowNum = 1;
            for (ErrorTestData errorTestData : errorTestDataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(errorTestData.getErrorcode());
                row.createCell(1).setCellValue(errorTestData.getExceptionMessages());
                row.createCell(2).setCellValue(errorTestData.getErrorMessages());
                row.createCell(3).setCellValue(errorTestData.getRca());
                row.createCell(4).setCellValue(errorTestData.getMitigation());
                row.createCell(5).setCellValue(errorTestData.getTimestamp());
                row.createCell(6).setCellValue(errorTestData.getUserId());
                row.createCell(7).setCellValue(errorTestData.getDeviceType());
                row.createCell(8).setCellValue(errorTestData.getAppVersion());
                row.createCell(9).setCellValue(errorTestData.getSessionId());
                row.createCell(10).setCellValue(errorTestData.getModule());
                row.createCell(11).setCellValue(errorTestData.getUsername());
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

