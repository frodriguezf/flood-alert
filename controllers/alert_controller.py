from database_access import guardeddatabase as gdb
from serializers.alert import alert_schema
from settings import DEFAULT_RADIUS


def get_alerts(*args, **kwargs):
    latitude = kwargs.get('lat')
    longitude = kwargs.get('lon')

    alerts = gdb.get_alerts(lat=latitude, lon=longitude, radius=DEFAULT_RADIUS)

    return alert_schema.dump(alerts, many=True).data
