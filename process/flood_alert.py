#!/usr/bin/env python
"""Usage:
    flood_alert.py --left-lat LB_LAT --left-lon LB_LON --right-lat RT_LAT --right-lon RT_LON [--date <date>]
    flood_alert.py --left-lat LB_LAT --left-lon LB_LON --right-lat RT_LAT --right-lon RT_LON [--date <date>] [--graph]

Alerts the flood detection in specific area at given date.

Examples:
  flood_alert.py --left-lat=-36.35 --left-lon=-66.20 --right-lat=-33.83 --right-lon=-59.58 --date=31/03/2013
  flood_alert.py --left-lat=-36.35 --left-lon=-66.20 --right-lat=-33.83 --right-lon=-59.58

Options:
  -h --help    Show this help
  --left-lat LB_LAT   Left Bottom point latitude of the area
  --left-lon LB_LON   Left Bottom point longitude of the area
  --right-lat RT_LAT  Right Top point latitude of the area
  --right-lon RT_LON  Right Top point ongitude of the area
  --date <date>       Date of interest (dd/mm/yyyy) (Optional)
  --graph             Only graph (Optional)
"""

from docopt import docopt
from datetime import date, datetime
import matplotlib.pyplot as plt
from pprint import pprint

from floodDetector import floodDetector
from api.flood_api import send_alarm_on_point
from utils import green, blue, red, yellow
from settings import DANGER_ALARM


def graph(fd_obj):
    plt.figure()

    # Mean, std, and max.
    plt.plot(fd_obj.get_dbuff(), label='mean')
    plt.plot(fd_obj.get_vbuff(), label='std')
    plt.plot(fd_obj.get_mbuff(), label='max')
    plt.grid(True)
    plt.legend(loc=0)

    # Surface graph.
    plt.figure()
    plt.plot(fd_obj.get_sbuff())
    plt.grid(True)
    plt.show()


def _get_date(date_arg):
    if date_arg is not None:
        return datetime.strptime(date_arg, '%d/%m/%Y').date()
    else:
        return date.today()


def process_alarm(fd_obj):
    # Check alarm.
    alarm_on = det0.get_alarm_status()
    cprint = green
    if alarm_on:
        cprint = red

    cprint("Alarm status for the region is: {}".format(alarm_on))

    if alarm_on:
        alarm_point = det0.get_max_position()
        severity = DANGER_ALARM
        date = det0.get_alarm_date()

        response = send_alarm_on_point(alarm_point, severity, date)

        #print("API response:")
        #print(pprint(response))


def detect(fd_obj):
    # Run detect for 3 days.
    for _ in xrange(3):
        green("Running processor on day {}".format(fd_obj.get_running_date()))
        fd_obj.detect()
        process_alarm(fd_obj)


if __name__ == '__main__':
    arguments = docopt(__doc__)

    # Today default value.
    date_arg = arguments.get('--date')
    date_obj = _get_date(date_arg)

    det0 = floodDetector()
    det0.set_init_date(date_obj.year, date_obj.month, date_obj.day)

    lb_lat = float(arguments.get('--left-lat'))
    lb_lon = float(arguments.get('--left-lon'))
    rt_lat = float(arguments.get('--right-lat'))
    rt_lon = float(arguments.get('--right-lon'))

    # Set region.
    det0.set_region(lb_lat, lb_lon, rt_lat, rt_lon)

    # Process the data.
    detect(det0)

    if arguments.get('--graph'):
        graph(det0)
