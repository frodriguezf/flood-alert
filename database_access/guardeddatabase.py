from models.alert import Alert


def get_alerts(lat=None, lon=None, radius=None):
    query_params = {key: value for (key, value) in
                    [('latitude', lat), ('longitude', lon)] if value is not None}

    return Alert.query.filter_by(**query_params).all()
