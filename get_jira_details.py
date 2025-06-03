import requests

# Jira credentials and URL
JIRA_URL = "https://automation25.atlassian.net"
AUTH = "Basic bWVnaGFuYTI1Y2hpdHR0aUBnbWFpbC5jb206QVRBVFQzeEZmR0YwQTVsQlRFRFl3V3hUSVRFOTI4UEpwZ3YtcWppNVVTanRhNVpDU2VwVWg1czNFMEN3Y3hnZnVtQ0JOVmhQNkI3eGQ2YmU3cHdhdjFaZ0lENDdhRmFrbFlEeDdxeVB1LUpuQjMzZ0VxZklvMTRvS0RPb2pCUmtBS3JocUVzY0VfOUlTWmpJcTRxM1NTRTRSTXQzYlgwSVhyWG5GUHNRbFFDNUt1ZUI0WjM3Ql84PTEwRkRFMTQ1"


# Headers for the request
headers = {
    "Content-Type": "application/json",
    "Authorization": AUTH
}


def get_comments(issue_key):
    url = JIRA_URL+"/rest/api/2/issue/"+issue_key
    response = requests.get(
        url,
        headers=headers
    )

    if response.status_code != 200:
        print(f"Failed to fetch comments for {issue_key}: {response.status_code}")
    else:
        text = ""
        comments_data = response.json()
        numberOfComments = len(comments_data["fields"]["comment"]["comments"])
        for i in range(0,numberOfComments):
            text = text + comments_data["fields"]["comment"]["comments"][i]["body"]+"\n"
        return text    

def fetch_jira_comments_by_errorcode(ErrorCode):
    ErrorCode = ErrorCode.split("-")[1]
    print(ErrorCode)
    url = JIRA_URL+"/rest/api/2/search/"
    query = {
    "jql": f"(project = 'SCRUM') AND (summary ~ \"{ErrorCode}\")"
    }

    response = requests.get(
        url,
        headers=headers,
        params=query
    )

    if response.status_code != 200:
        print(f"Failed to fetch comments for {query}: {response.status_code}")
    else:
        data = response.json()
        #print(data)
        with open("jira_summary.txt", "w") as file:
            if len(data["issues"])>0:
                for i in range(0,len(data["issues"])):
                    issue = data["issues"][i]["key"]
                    file.write(get_comments(issue))
            else:
                file.write("JIRA ISSUE NOT FOUND")
        file.close