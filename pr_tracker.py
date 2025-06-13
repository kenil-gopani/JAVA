import os
import requests
from datetime import datetime, timedelta
import json

# Get environment variables set by GitHub Actions
# --- UPDATED LINE BELOW ---
GITHUB_TOKEN = os.getenv('GTH_TOK') # Now using the secret name GTH_TOK
# --- END UPDATED LINE ---
GITHUB_USERNAME = os.getenv('GH_USERNAME') # Your GitHub username (make sure this is also set in the action)

def get_merged_prs(username, start_date_str):
    base_url = "https://api.github.com/search/issues"
    headers = {
        "Authorization": f"token {GITHUB_TOKEN}",
        "Accept": "application/vnd.github.v3+json",
    }

    query = f"is:pr is:merged author:{username} merged:>{start_date_str}"

    all_merged_prs = []
    page = 1

    while True:
        params = {"q": query, "per_page": 100, "page": page}
        response = requests.get(base_url, headers=headers, params=params)
        response.raise_for_status()

        data = response.json()
        items = data.get('items', [])

        if not items:
            break

        all_merged_prs.extend(items)

        if len(items) < 100:
            break
        page += 1

    return len(all_merged_prs)

if __name__ == "__main__":
    today = datetime.now()

    start_of_day = datetime(today.year, today.month, today.day, 0, 0, 0)
    start_of_month = datetime(today.year, today.month, 1, 0, 0, 0)

    prs_today = get_merged_prs(GITHUB_USERNAME, start_of_day.isoformat(timespec='seconds'))
    prs_month = get_merged_prs(GITHUB_USERNAME, start_of_month.isoformat(timespec='seconds'))

    print(f"Daily Merged PRs: {prs_today}")
    print(f"Monthly Merged PRs: {prs_month}")

    with open(os.environ['GITHUB_OUTPUT'], 'a') as fh:
        print(f'prs_today={prs_today}', file=fh)
        print(f'prs_month={prs_month}', file=fh)
