import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class HTMLGenerator {

    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\modiy\\Downloads\\failed_cases_with_predictions.xlsx";
        String htmlFilePath = "New_test_report.html";

        Map<String, String> columnMapping = new LinkedHashMap<>();
        columnMapping.put("testId", "Test ID");
        columnMapping.put("scenario", "Test Summary");
        columnMapping.put("status", "Test Status");
        columnMapping.put("errorCode", "Error Code");
        columnMapping.put("errorMessage", "Error Message");
        columnMapping.put("exceptionMessage", "Log Exception");
        columnMapping.put("Similarity_Score", "Similarity Score");
        columnMapping.put("Predicted_RCA", "Probable Failure Cause");
        columnMapping.put("Predicted_Mitigation", "Remediation Steps");

        convertExcelToHtml(excelFilePath, htmlFilePath, columnMapping);
    }

    public static void convertExcelToHtml(String excelPath, String htmlPath, Map<String, String> columnMap) {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(htmlPath))) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            Map<String, Integer> columnIndices = new HashMap<>();

            for (Cell cell : headerRow) {
                String normalizedHeader = cell.getStringCellValue().trim();
                if (columnMap.containsKey(normalizedHeader)) {
                    columnIndices.put(normalizedHeader, cell.getColumnIndex());
                }
            }

            // Begin HTML
            writer.write("<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Test Report</title>");
            writer.write("<style>");
            writer.write("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 40px; background-color: #f5f5f5; }");
            writer.write("h2 { text-align: center; color: #333; }");
            writer.write("table { border-collapse: collapse; width: 100%; background-color: white; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }");
            writer.write("th, td { padding: 12px 16px; text-align: left; border-bottom: 1px solid #ddd; font-size: 14px; }");
            writer.write("th { background-color: #cce5ff; color: #003366; position: sticky; top: 0; z-index: 2; }");
            writer.write("tr:nth-child(even) { background-color: #f9f9f9; }");
            writer.write("tr:hover { background-color: #f1f1f1; }");
            writer.write(".status-PASSED { color: green; font-weight: bold; }");
            writer.write(".status-FAILED { color: red; font-weight: bold; }");
            writer.write(".status-SKIPPED { color: orange; font-weight: bold; }");
            writer.write("</style></head><body>");

            writer.write("<h2>MELT Failure Analysis Report</h2>");
            writer.write("<table>");
            writer.write("<thead><tr>");

            for (String colKey : columnMap.keySet()) {
                writer.write("<th>" + columnMap.get(colKey) + "</th>");
            }
            writer.write("</tr></thead><tbody>");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                writer.write("<tr>");
                for (String excelCol : columnMap.keySet()) {
                    int colIndex = columnIndices.getOrDefault(excelCol, -1);
                    String cellValue = "";

                    if (colIndex != -1) {
                        Cell cell = row.getCell(colIndex);
                        if (cell != null) {
                            cellValue = getCellValueAsString(cell);
                        }
                    }

                    if (excelCol.equalsIgnoreCase("status")) {
                        String cssClass = "status-" + cellValue.toUpperCase();
                        writer.write("<td class='" + cssClass + "'>" + cellValue + "</td>");
                    } else {
                        writer.write("<td>" + cellValue + "</td>");
                    }
                }
                writer.write("</tr>");
            }

            writer.write("</tbody></table></body></html>");
            System.out.println("✅ Report successfully generated at: " + htmlPath);

        } catch (Exception e) {
            System.err.println("❌ Error generating HTML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }
}
