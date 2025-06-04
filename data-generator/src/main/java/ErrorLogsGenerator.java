import com.github.javafaker.Faker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ErrorLogsGenerator implements Errors{

    public static void main(String[] args) throws IOException {
        // Base time: 5th June 2025, 07:21:00
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 5, 7, 21, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long baseTime = calendar.getTimeInMillis();
        Faker faker = new Faker();
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        List<ErrorTestData> errorTestDataList = new ArrayList<>();
        int numberOfRowsToGenerate =Integer.parseInt(System.getProperty("numberOfRows","20"));
        for(int i =0;i<numberOfRowsToGenerate;i++){

            ErrorTestData errorTestData = new ErrorTestData();
            //String errorCode = errorCodes[random.nextInt(errorCodes.length)];
            errorTestData.setErrorcode(errorCodes[random.nextInt(errorCodes.length)]);
            //String exceptionMessage = exceptionMessages[random.nextInt(exceptionMessages.length)];
            errorTestData.setExceptionMessages(exceptionMessages[random.nextInt(exceptionMessages.length)]);
            //String errorMessage = errorMessages[random.nextInt(errorMessages.length)];
            errorTestData.setErrorMessages(errorMessages[random.nextInt(errorMessages.length)]);
            //String rca = rcas[random.nextInt(rcas.length)];
            errorTestData.setRca(rcas[random.nextInt(rcas.length)]);
            //String mitigation = mitigations[random.nextInt(mitigations.length)];
            errorTestData.setMitigation(mitigations[random.nextInt(mitigations.length)]);

            // Timestamp increases 30 seconds per entry
            Date currentDate = new Date(baseTime + i * 30000L);
            String timestamp = sdf.format(currentDate);
            errorTestData.setUserId(faker.idNumber().valid());
            errorTestData.setDeviceType(faker.options().option("ANDROID","iOS","WEB"));
            errorTestData.setAppVersion(faker.app().version());
            errorTestData.setSessionId(DataGeneratorUtils.generateRandomSessionId(12,random));
            errorTestData.setModule(faker.options().option("AuthService", "Bookstack", "Inventory", "Checkout", "Billing"));
            errorTestData.setUsername(faker.options().option("ananya_r", "raj_kapoor", "mohan.v", "rhea.d", "mark_johnson"));
            errorTestDataList.add(errorTestData);
            }
        ExportErrorDataToExcel.exportDataToExcel(errorTestDataList);
    }
}
