package com.automation.testreport;

public class TestReport {
    private String testId;
    private String errorCode="NOT FOUND";
    private String errorMessage="NOT FOUND";
    private String exceptionMessage="NOT FOUND";
    private String sessionId;
    private String timestamp;
    private String enviromentDetails;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEnviromentDetails() {
        return enviromentDetails;
    }

    public void setEnviromentDetails(String enviromentDetails) {
        this.enviromentDetails = enviromentDetails;
    }

    @Override
    public String toString() {
        return "TestReport{" +
                "testId='" + testId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", enviromentDetails='" + enviromentDetails + '\'' +
                '}';
    }
}
