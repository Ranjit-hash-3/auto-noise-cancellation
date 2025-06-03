import requests
import sys


# Jira credentials and URL
JIRA_URL = "https://automation25.atlassian.net"
AUTH = "Basic bWVnaGFuYTI1Y2hpdHR0aUBnbWFpbC5jb206QVRBVFQzeEZmR0YwQTVsQlRFRFl3V3hUSVRFOTI4UEpwZ3YtcWppNVVTanRhNVpDU2VwVWg1czNFMEN3Y3hnZnVtQ0JOVmhQNkI3eGQ2YmU3cHdhdjFaZ0lENDdhRmFrbFlEeDdxeVB1LUpuQjMzZ0VxZklvMTRvS0RPb2pCUmtBS3JocUVzY0VfOUlTWmpJcTRxM1NTRTRSTXQzYlgwSVhyWG5GUHNRbFFDNUt1ZUI0WjM3Ql84PTEwRkRFMTQ1"
project = "SCRUM"
summary = "Unable to complete E2E journey due to ERR 2002"
description = """Steps to reproduce:
1. Go to login page
2. Click login
Expected: Dashboard loads
Actual: 500 error"""
issue_type = "Bug"

# Headers for the request
headers = {
    "Content-Type": "application/json",
    "Authorization": AUTH
}

def create_jira_payload(project_key, summary, description, issue_type):
    payload = {
        "fields": {
            "project": { "key": project_key },
            "summary": summary,
            "description": description,
            "issuetype": { "name": issue_type }
        }
    }
    return payload


def create_jira_bug(project, summary, description, issue_type):
    url = JIRA_URL+"/rest/api/2/issue/"
    payload = create_jira_payload(project,summary,description,issue_type)
    response = requests.post(
        url,
        headers=headers,
        json=payload
    )

    if response.status_code != 201:
        print(f"Failed to create bug comments : {response.status_code}")
    else:
        issuejson = response.json()
        print(issuejson["key"])
        return issuejson["key"]
           



if __name__ == "__main__":
    project = str(sys.argv[1])
    summary = str(sys.argv[2])
    description = str(sys.argv[3])
    issue_type = str(sys.argv[4])
    create_jira_bug(project, summary, description, issue_type)
   