# flood-alert

Flood alert for space challenge 2017

# Execution
```bash
Usage:
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
```

## Ejemplo de ejecución
```bash
./flood_alert.py --left-lat=-36.35 --left-lon=-66.20 --right-lat=-33.83 --right-lon=-59.58 --date=03/04/2013
```
