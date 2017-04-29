import random

from flask_script import Command
from models.alert import db, Alert


class Seed(Command):
    def run(self):
        choices = [Alert.WARNING, Alert.CAUTION, Alert.DANGER]
        lat_min = -33.0
        lon_min = -64.0

        for i in xrange(10):
            alert = Alert(latitude=lat_min + random.random(),
                          longitude=lon_min + random.random(),
                          severity=random.choice(choices))

            db.session.add(alert)
            db.session.commit()
