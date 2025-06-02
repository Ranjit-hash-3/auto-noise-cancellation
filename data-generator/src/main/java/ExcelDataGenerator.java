//This file is to generate excel logs

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class ExcelDataGenerator {

    // Helper to generate random alphanumeric session ID
    public static String generateRandomSessionId(int length, Random rand) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] errorCodes = {
                "ERR-1001", "ERR-2002",
                "ERR-3003", "ERR-4004",
                "ERR-5005"
        };

        String[] exceptionMsgs = {
                "Your session has expired. Please log in again.",
                "Something went wrong. Please try again later.",
                "Sorry, your request cannot be processed. Please check your input.",
                "The requested resource could not be found.",
                "Sorry, your request cannot processed. Please try after sometime."
        };

        String[] errorMessages = {
                "connection timeout",
                "Authentication failed",
                "Invalid input parameter",
                "Resource not found",
                "Internal server error"
        };

        String[] deviceTypes = {"ANDROID", "IOS", "WEB"};
        String[] usernames = {"ananya_r", "raj_kapoor", "mohan.v", "rhea.d", "mark_johnson"};
        String[] modules = {"AuthService", "Bookstack", "Inventory", "Checkout", "Billing"};

        Random rand = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        // Set base date to 5th June 2025, 07:21:00
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 5, 7, 21, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long baseTime = calendar.getTimeInMillis();

        // Create workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Server Error Logs");

        // Create header row
        Row header = sheet.createRow(0);
        String[] columns = {"errorCode", "exceptionMessage", "timestamp", "userId", "deviceType",
                "appVersion", "sessionId", "errorMessage", "module", "username"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }

        try {
            for (int i = 0; i < 5000; i++) {
                int idx = rand.nextInt(errorCodes.length);
                String errorCode = errorCodes[idx];
                String errorMessage = errorMessages[idx];
                String exceptionMessage = exceptionMsgs[idx];

                // Increment timestamp by 30 seconds per log
                Date currentDate = new Date(baseTime + i * 30000);
                String timestamp = sdf.format(currentDate);

                String userId = rand.nextInt(90000) + 10000 + "-" + rand.nextInt(90) + "-" + rand.nextInt(90000);
                String deviceType = deviceTypes[rand.nextInt(deviceTypes.length)];
                String username = usernames[rand.nextInt(usernames.length)];
                String appVersion = rand.nextInt(6) + "." + rand.nextInt(10) + "." + rand.nextInt(10);
                String module = modules[rand.nextInt(modules.length)];

                String sessionId = generateRandomSessionId(12, rand);

                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(errorCode);
                row.createCell(1).setCellValue(exceptionMessage);
                row.createCell(2).setCellValue(timestamp);
                row.createCell(3).setCellValue(userId);
                row.createCell(4).setCellValue(deviceType);
                row.createCell(5).setCellValue(appVersion);
                row.createCell(6).setCellValue(sessionId);
                row.createCell(7).setCellValue(errorMessage);
                row.createCell(8).setCellValue(module);
                row.createCell(9).setCellValue(username);
            }

            // Auto-size all columns
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write workbook to file
            try (FileOutputStream fos = new FileOutputStream("server_error_log.xlsx")) {
                workbook.write(fos);
            }

            workbook.close();

            System.out.println("✅ Excel file generated successfully: server_error_log.xlsx");

        } catch (IOException e) {
            System.err.println("❌ Failed to write Excel file: " + e.getMessage());
        }
    }
}
