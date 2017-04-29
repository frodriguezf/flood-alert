from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


class Alert(db.Model):
    WARNING = 'WARNING'
    CAUTION = 'CAUTION'
    DANGER = 'DANGER'

    id = db.Column(db.Integer, primary_key=True, unique=True)
    latitude = db.Column(db.Float)
    longitude = db.Column(db.Float)
    severity = db.Column(db.String(20))
