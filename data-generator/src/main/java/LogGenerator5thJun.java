//This file is to generate log file for 5th Jun

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class LogGenerator5thJun {

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

        // Set base date to 4th June 2025, 00:00:00
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 5, 7, 21, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long baseTime = calendar.getTimeInMillis();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("server_error_log.txt"))) {
            for (int i = 0; i < 5000; i++) {
                int idx = rand.nextInt(errorCodes.length);
                String errorCode = errorCodes[idx];
                String errorMessage = errorMessages[idx];
                String exceptionMessage = exceptionMsgs[idx];

                // Increment timestamp by 30 seconds per log
                Date currentDate = new Date(baseTime + i * 30000);  // 30000 ms = 30 seconds
                String timestamp = sdf.format(currentDate);

                String userId = rand.nextInt(90000) + 10000 + "-" + rand.nextInt(90) + "-" + rand.nextInt(90000);
                String deviceType = deviceTypes[rand.nextInt(deviceTypes.length)];
                String username = usernames[rand.nextInt(usernames.length)];
                String appVersion = rand.nextInt(6) + "." + rand.nextInt(10) + "." + rand.nextInt(10);
                String module = modules[rand.nextInt(modules.length)];

                // Generate random session ID of length 12
                String sessionId = generateRandomSessionId(12, rand);

                // Simulate realistic pre- and post-log lines
                String preLog = String.format("[INFO] %s [thread-%d] com.server.%s - Entering %s module...",
                        timestamp, rand.nextInt(10), module.toLowerCase(), module);

                String jsonLog = String.format("{\n" +
                                "  \"errorCode\": \"%s\",\n" +
                                "  \"errorMessage\": \"%s\",\n" +
                                "  \"timestamp\": \"%s\",\n" +
                                "  \"userId\": \"%s\",\n" +
                                "  \"deviceType\": \"%s\",\n" +
                                "  \"appVersion\": \"%s\",\n" +
                                "  \"sessionId\": \"%s\",\n" +
                                "  \"Exception\": \"FATAL - %s in module %s. User: %s\"\n" +
                                "}", errorCode, exceptionMessage, timestamp, userId, deviceType, appVersion,
                        sessionId,
                        errorMessage, module, username);

                String postLog = String.format("[DEBUG] %s [thread-%d] com.server.logger - Log captured successfully.\n",
                        timestamp, rand.nextInt(10));

                writer.write(preLog + "\n");
                writer.write(jsonLog + "\n");
                writer.write(postLog + "\n");
                writer.write("------------------------------------------------------------\n");
            }
            System.out.println("✅ Log file generated successfully: server_error_log.txt");
        } catch (IOException e) {
            System.err.println("❌ Failed to write log file: " + e.getMessage());
        }
    }
}
