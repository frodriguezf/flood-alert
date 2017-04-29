from app import db


class Alert(db.Model):
    id = db.Column(db.Integer, primary_key=True, unique=True)
    latitude = db.Column(db.Float)
    longitude = db.Column(db.Float)
    severity = db.Column(db.String(20))
