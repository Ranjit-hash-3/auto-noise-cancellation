import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LogGenerator {
    public static void main(String[] args) {
        String[] errorCodes = {
                "ERR-1001", "ERR-2002",
                "ERR-3003", "ERR-4004",
                "ERR-5005"
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("server_error_log.txt"))) {
            for (int i = 0; i < 5000; i++) {
                int idx = rand.nextInt(errorCodes.length);
                String errorCode = errorCodes[idx];
                String errorMessage = errorMessages[idx];
                String timestamp = sdf.format(new Date());
                String userId = rand.nextInt(90000) + 10000 + "-" + rand.nextInt(90) + "-" + rand.nextInt(90000);
                String deviceType = deviceTypes[rand.nextInt(deviceTypes.length)];
                String username = usernames[rand.nextInt(usernames.length)];
                String appVersion = rand.nextInt(6) + "." + rand.nextInt(10) + "." + rand.nextInt(10);
                String module = modules[rand.nextInt(modules.length)];

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
                                "  \"logEntry\": \"FATAL - %s: %s in module %s. User: %s\"\n" +
                                "}", errorCode, errorMessage, timestamp, userId, deviceType, appVersion,
                        errorCode, errorMessage, module, username);

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
