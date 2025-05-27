import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ErrorLogsGenerator implements Errors{

    public static void main(String[] args) throws IOException {
        Faker faker = new Faker();
        Random random = new Random();
        List<ErrorTestData> errorTestDataList = new ArrayList<>();
        int numberOfRowsToGenerate =Integer.parseInt(System.getProperty("numberOfRows","20"));
        for(int i =0;i<numberOfRowsToGenerate;i++){
            int errorIndex = random.nextInt(errorCodes.length);
            String errorCode = errorCodes[errorIndex];
            String errorMessage = errorMessages[errorIndex];
            String logEntry = String.format("[%s] %s - %s: %s in module %s. User: %s, SessionID: %s",
                    faker.date().past(7, TimeUnit.DAYS),
                    faker.options().option("ERROR","WARN","FATAL"),
                    errorCode,
                    errorMessage,
                    faker.app().name(),
                    faker.name().username(),
                    faker.internet().uuid()
                    );
            ErrorTestData errorTestData = new ErrorTestData();
            errorTestData.setErrorcode(errorCode);
            errorTestData.setErrorMessage(errorMessage);
            errorTestData.setTimestamp(faker.date().past(7,TimeUnit.DAYS).toString());
            errorTestData.setLogEntry(logEntry);
            errorTestData.setUserId(faker.idNumber().valid());
            errorTestData.setDeviceType(faker.options().option("ANDROID","iOS"));
            errorTestData.setAppVersion(faker.app().version());
            errorTestDataList.add(errorTestData);
        }
        ExportErrorDataToExcel.exportDataToExcel(errorTestDataList);
    }
}
