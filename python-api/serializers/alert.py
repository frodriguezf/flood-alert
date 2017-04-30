from marshmallow_sqlalchemy import ModelSchema

from models.alert import Alert


class AlertSchema(ModelSchema):
    class Meta:
        model = Alert


alert_schema = AlertSchema()
