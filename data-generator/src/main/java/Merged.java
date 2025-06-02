//This file is merged version for log and excel


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Merged {

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

        // Base timestamp: 5th June 2025, 07:21:00
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 5, 7, 21, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long baseTime = calendar.getTimeInMillis();

        // Excel setup
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Server Error Logs");
        String[] columns = {"errorCode", "errorMessage", "timestamp", "userId", "deviceType",
                "appVersion", "sessionId", "exceptionMessage", "module", "username"};
        Row header = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        try (
                FileOutputStream fos = new FileOutputStream("server_error_log.xlsx");
                BufferedWriter writer = new BufferedWriter(new FileWriter("server_error_log.txt"))
        ) {
            for (int i = 0; i < 5000; i++) {
                int idx = rand.nextInt(errorCodes.length);
                String errorCode = errorCodes[idx];
                String exceptionMessage = exceptionMsgs[idx];
                String errorMessage = errorMessages[idx];

                Date currentDate = new Date(baseTime + i * 30000);
                String timestamp = sdf.format(currentDate);

                String userId = (rand.nextInt(90000) + 10000) + "-" + rand.nextInt(90) + "-" + rand.nextInt(90000);
                String deviceType = deviceTypes[rand.nextInt(deviceTypes.length)];
                String appVersion = rand.nextInt(6) + "." + rand.nextInt(10) + "." + rand.nextInt(10);
                String sessionId = generateRandomSessionId(12, rand);
                String module = modules[rand.nextInt(modules.length)];
                String username = usernames[rand.nextInt(usernames.length)];
                int threadId = rand.nextInt(10);

                // Excel write
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

                // Log file write
                String preLog = String.format(
                        "[INFO] %s [thread-%d] com.server.%s - Entering %s module...",
                        timestamp, threadId, module.toLowerCase(), module
                );

                String jsonLog = String.format(
                        "{\n" +
                                "  \"errorCode\": \"%s\",\n" +
                                "  \"errorMessage\": \"%s\",\n" +
                                "  \"timestamp\": \"%s\",\n" +
                                "  \"userId\": \"%s\",\n" +
                                "  \"deviceType\": \"%s\",\n" +
                                "  \"appVersion\": \"%s\",\n" +
                                "  \"sessionId\": \"%s\",\n" +
                                "  \"Exception\": \"FATAL - %s in module %s. User: %s\"\n" +
                                "}",
                        errorCode, exceptionMessage, timestamp, userId, deviceType,
                        appVersion, sessionId, errorMessage, module, username
                );

                String postLog = String.format(
                        "[DEBUG] %s [thread-%d] com.server.logger - Log captured successfully.",
                        timestamp, threadId
                );

                writer.write(preLog + "\n");
                writer.write(jsonLog + "\n");
                writer.write(postLog + "\n");
                writer.write("------------------------------------------------------------\n");
            }

            // Finalize Excel
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fos);
            workbook.close();

            System.out.println("✅ Both Excel and log file have been generated successfully.");
        } catch (IOException e) {
            System.err.println("❌ Error writing files: " + e.getMessage());
        }
    }
}
