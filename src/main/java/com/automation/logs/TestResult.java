package com.automation.logs;

public class TestResult {
    private String testId;
    private String errorCode;
    private String errorMessage;
    private String logEntry;

    public TestResult(String testId,
                      String errorCode, String errorMessage, String logEntry) {
        this.testId = testId;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.logEntry = logEntry;
    }

    public String getTestId() { return testId; }
    public String getErrorCode() { return errorCode; }
    public String getErrorMessage() { return errorMessage; }
    public String getLogEntry() { return logEntry; }
}
