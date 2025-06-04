public interface Errors {
    String[] errorCodes = {
            "ERR-1001","ERR-2002",
            "ERR-3003","ERR-4004",
            "ERR-5005","ERR-6006", "ERR-7007", "ERR-8008", "ERR-9009", "ERR-1010"
    };

    String[] exceptionMessages = {
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

    String[] rcas = {
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

    String[] mitigations = {
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

}
