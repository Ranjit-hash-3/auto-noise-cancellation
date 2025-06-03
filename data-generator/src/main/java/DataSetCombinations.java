// This file is to generate combination of data sets

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DataSetCombinations {

    // Random session ID generator
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
                "ERR-1001", "ERR-2002", "ERR-3003", "ERR-4004", "ERR-5005",
                "ERR-6006", "ERR-7007", "ERR-8008", "ERR-9009", "ERR-1010"
        };

        String[] errorMessages = {
                "Your session has expired. Please log in again.",
                "Something went wrong. Please try again later.",
                "Request cannot be processed. Please check input.",
                "The requested resource is missing.",
                "Unexpected error occurred during processing.",
                "Database connection failed.",
                "Service temporarily unavailable.",
                "Permission denied. Access restricted.",
                "Timeout while connecting to backend services.",
                "Data validation failed."
        };

        String[] exceptionMsgs = {
                "connection timeout",
                "authentication failure",
                "invalid parameter values",
                "resource unavailable",
                "null pointer exception",
                "database unreachable",
                "unauthorized access attempt",
                "internal processing delay",
                "input validation error",
                "external API failure"
        };

        String[] RCA = {
                "Network congestion or ISP-level issue",
                "Expired or missing session tokens",
                "Malformed JSON or XML payloads",
                "Load balancer misrouting traffic",
                "Database max connections reached",
                "Insufficient permissions on backend",
                "Third-party service SLA breach",
                "Memory leak in critical module",
                "Incorrect configuration in deployment",
                "Clock skew between client and server"
        };

        String[] Mitigation = {
                "Add client-side retries with exponential backoff",
                "Implement token refresh & proactive expiration handling",
                "Sanitize and validate input before sending to server",
                "Ensure sticky sessions in load balancer settings",
                "Tune database connection pool limits and timeouts",
                "Review IAM policies and RBAC settings",
                "Enable fallback mechanisms for external dependencies",
                "Run heap profiler & fix memory retention issues",
                "Automate config validation during CI/CD pipelines",
                "Synchronize system clocks using NTP"
        };

        String[] deviceTypes = {"ANDROID", "IOS", "WEB"};
        String[] usernames = {"ananya_r", "raj_kapoor", "mohan.v", "rhea.d", "mark_johnson"};
        String[] modules = {"AuthService", "Bookstack", "Inventory", "Checkout", "Billing"};

        Random rand = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        // Base time: 5th June 2025, 07:21:00
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 5, 7, 21, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long baseTime = calendar.getTimeInMillis();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Randomized Error Logs");

        // Header row
        String[] columns = {
                "errorCode", "exceptionMessage", "errorMessage", "RCA", "Mitigation",
                "timestamp", "userId", "deviceType", "appVersion", "sessionId", "module", "username"
        };

        Row header = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }

        // Generate 5000 entries
        for (int i = 0; i < 5000; i++) {

            // Random error-related values
            String errorCode = errorCodes[rand.nextInt(errorCodes.length)];
            String exceptionMessage = exceptionMsgs[rand.nextInt(exceptionMsgs.length)];
            String errorMessage = errorMessages[rand.nextInt(errorMessages.length)];
            String rca = RCA[rand.nextInt(RCA.length)];
            String mitigation = Mitigation[rand.nextInt(Mitigation.length)];

            // Timestamp increases 30 seconds per entry
            Date currentDate = new Date(baseTime + i * 30000);
            String timestamp = sdf.format(currentDate);

            // Random user/session/device values
            String userId = rand.nextInt(90000) + 10000 + "-" + rand.nextInt(90) + "-" + rand.nextInt(90000);
            String deviceType = deviceTypes[rand.nextInt(deviceTypes.length)];
            String appVersion = rand.nextInt(6) + "." + rand.nextInt(10) + "." + rand.nextInt(10);
            String sessionId = generateRandomSessionId(12, rand);
            String module = modules[rand.nextInt(modules.length)];
            String username = usernames[rand.nextInt(usernames.length)];

            // Fill row
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(errorCode);
            row.createCell(1).setCellValue(exceptionMessage);
            row.createCell(2).setCellValue(errorMessage);
            row.createCell(3).setCellValue(rca);
            row.createCell(4).setCellValue(mitigation);
            row.createCell(5).setCellValue(timestamp);
            row.createCell(6).setCellValue(userId);
            row.createCell(7).setCellValue(deviceType);
            row.createCell(8).setCellValue(appVersion);
            row.createCell(9).setCellValue(sessionId);
            row.createCell(10).setCellValue(module);
            row.createCell(11).setCellValue(username);
        }

        // Auto-size columns
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write Excel
        try (FileOutputStream fos = new FileOutputStream("error_log_combined.xlsx")) {
            workbook.write(fos);
            workbook.close();
            System.out.println("✅ Excel file created: error_log_combined.xlsx");
        } catch (IOException e) {
            System.err.println("❌ Error writing Excel file: " + e.getMessage());
        }
    }
}
