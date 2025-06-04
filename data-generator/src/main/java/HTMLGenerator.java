// This file is to generate HTML from excel

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class HTMLGenerator {

    public static void main(String[] args) {
        String excelFilePath = "C:\\Users\\modiy\\Downloads\\failed_cases_with_predictions.xlsx";
        String htmlFilePath = "test_report.html";

        // Mapping Excel headers to desired HTML headers
        Map<String, String> columnMapping = new LinkedHashMap<>();
        columnMapping.put("testId", "Test ID");
        columnMapping.put("scenario", "Test Summary");
        columnMapping.put("status", "Test Status");
        columnMapping.put("errorCode", "Error Code");
        columnMapping.put("errorMessage", "Error Message");
        columnMapping.put("exceptionMessage", "Log Exception");
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

            // Map column names to their indices in Excel
            for (Cell cell : headerRow) {
                String columnName = cell.getStringCellValue().trim();
                if (columnMap.containsKey(columnName)) {
                    columnIndices.put(columnName, cell.getColumnIndex());
                }
            }

            writer.write("<html><head><title>Test Results</title>");
            writer.write("<style>table { border-collapse: collapse; width: 100%; }");
            writer.write("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            writer.write("th { background-color: #f2f2f2; }</style></head><body>");
            writer.write("<h2>Test Failure Report</h2>");
            writer.write("<table>");
            writer.write("<tr>");

            // Write HTML table headers
            for (String excelCol : columnMap.keySet()) {
                writer.write("<th>" + columnMap.get(excelCol) + "</th>");
            }
            writer.write("</tr>");

            // Write data rows
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
                    writer.write("<td>" + cellValue + "</td>");
                }
                writer.write("</tr>");
            }

            writer.write("</table></body></html>");
            System.out.println("HTML report generated at: " + htmlPath);

        } catch (Exception e) {
            System.err.println("Error processing Excel file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            case BLANK: return "";
            default: return "UNKNOWN";
        }
    }
}
