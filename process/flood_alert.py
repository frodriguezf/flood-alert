#!/usr/bin/env python
"""Usage:
    flood_alert.py --left-lat LB_LAT --left-lon LB_LON --right-lat RT_LAT --right-lon RT_LON [--date <date>]

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
  --date <date>      Date of interest (dd/mm/yyyy) (Optional)
"""

from docopt import docopt
from datetime import date, datetime

from floodDetector import floodDetector
import matplotlib.pyplot as plt


# det0 = fd()
# det0.set_init_date(2013,3,31)
# det0.set_region(-36.35, -66.20, -33.83, -59.58)
# det0.detect()
# det0.detect()
# det0.detect()
# det0.detect()
# det0.detect()
#
#
#
#
# plt.figure()
# plt.plot(det0.get_dbuff(),label='mean')
# plt.plot(det0.get_vbuff(),label='std')
# plt.plot(det0.get_mbuff(),label='max')
# plt.grid(True)
# plt.legend(loc=0)
#
# plt.figure()
# plt.plot(det0.get_sbuff())
# plt.grid(True)
#
# plt.show()

def _get_date(date_arg):
    if date_arg is not None:
        return datetime.strptime(date_arg, '%d/%m/%Y').date()
    else:
        return date.today()


if __name__ == '__main__':
    arguments = docopt(__doc__)

    # Today default value.
    date_arg = arguments.get(date)
    date_obj = _get_date(date_arg)

    det0 = floodDetector()
    det0.set_init_date(date_obj.year, date_obj.month, date_obj.day)

    lb_lat = float(arguments.get('--left-lat'))
    lb_lon = float(arguments.get('--left-lon'))
    rt_lat = float(arguments.get('--right-lat'))
    rt_lon = float(arguments.get('--right-lon'))

    # Set region.
    det0.set_region(lb_lat, lb_lon, rt_lat, rt_lon)

    det0.detect()
