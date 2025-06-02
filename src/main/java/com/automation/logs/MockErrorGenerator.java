package com.automation.logs;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class MockErrorGenerator {
    private static final ArrayList<ErrorLog> ERROR_LOGS = new ArrayList<>();
    private static final Random random = new Random();
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("EEE MMMM dd HH:mm:ss z yyyy");
    static {

        ERROR_LOGS.add(new ErrorLog(
                "ERR-5005",
                "Internal server error",
                "693-21-5419",
                "ANDROID",
                "4.9",
                "ERROR - ERR-5005: Internal server error in module Hatity. User: kasie.ankunding, SessionID: 0f210e9c-8293-4087-bfed-98d284d7bc54"
        ));

        ERROR_LOGS.add(new ErrorLog(
                "ERR-2002",
                "Authentication failed",
                "518-16-3052",
                "iOS",
                "0.1.2",
                "ERROR - ERR-2002: Authentication failed in module Otcom. User: curtis.lakin, SessionID: 1fdcd4c4-5740-467c-b533-2e00b3818c59"
        ));

        ERROR_LOGS.add(new ErrorLog(
                "ERR-4004",
                "Resource not found",
                "194-44-4147",
                "ANDROID",
                "2.6",
                "FATAL - ERR-4004: Resource not found in module Tampflex. User: miles.schuster, SessionID: 292a6028-2064-46f1-98a4-2d90283bd12f"
        ));

        ERROR_LOGS.add(new ErrorLog(
                "ERR-3003",
                "Invalid input parameter",
                "747-16-2494",
                "iOS",
                "0.85",
                "WARN - ERR-3003: Invalid input parameter in module Tempsoft. User: kyle.windler, SessionID: 5f98e075-91e7-4882-bec8-79b4bb6920ea"
        ));

        ERROR_LOGS.add(new ErrorLog(
                "ERR-1001",
                "connection timeout",
                "824-62-1573",
                "ANDROID",
                "3.80",
                "WARN - ERR-1001: connection timeout in module Fixflex. User: nicky.stracke, SessionID: b92e0e36-d08c-4864-b072-bcf6b0a5868f"
        ));
    }
    public static JSONObject generateRandomErrorJson()
    {
        ErrorLog errorLog = ERROR_LOGS.get(random.nextInt(ERROR_LOGS.size()));
        String timestamp = TIMESTAMP_FORMAT.format(new Date());
        String logEntry = "["+timestamp+"]"+errorLog.logEntry;
        JSONObject json = new JSONObject();
        json.put("errorCode",errorLog.errorCode);
        json.put("errorMessage",errorLog.errorMessage);
        json.put("timestamp",timestamp);
        json.put("userId",errorLog.userId);
        json.put("deviceType",errorLog.deviceType);
        json.put("appVersion",errorLog.appVersion);
        json.put("logEntry",errorLog.logEntry);
        return json;
    }

    private static class ErrorLog
    {
        String errorCode;
        String errorMessage;
        String userId;
        String deviceType;
        String appVersion;
        String logEntry;

        public ErrorLog(String errorCode, String errorMessage, String userId, String deviceType, String appVersion, String logEntry) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
            this.userId = userId;
            this.deviceType = deviceType;
            this.appVersion = appVersion;
            this.logEntry = logEntry;
        }
    }

}