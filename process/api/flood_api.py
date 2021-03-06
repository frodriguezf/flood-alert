"""
EXAMPLE_PAYLOAD = {
  "date": "2017-04-30",
  "message": "Flood detection in point",
  "severity": "WARNING",
  "latitude": 0,
  "longitude": 0
}
"""

import requests
import json

from settings import API_ENDPOINT


def _build_payload(lat, lon, severity, date, msg=None):
    payload = dict()
    payload['date'] = date.strftime('%Y-%m-%d')
    payload['latitude'] = lat
    payload['longitude'] = lon
    payload['severity'] = severity
    payload['message'] = msg or "Flood detection"

    return json.dumps(payload)


def send_alarm_on_point(point, severity, date):
    payload = _build_payload(point[0], point[1], severity, date)
    headers = {'Content-type': 'application/json', 'Accept': 'application/json'}
    r = requests.post(API_ENDPOINT, data=payload, headers=headers)

    return r.json()
