import os
basedir = os.path.abspath(os.path.dirname(__file__))

SQLALCHEMY_DATABASE_URI = 'sqlite:///' + os.path.join(basedir, 'flood_app.db')
SQLALCHEMY_MIGRATE_REPO = os.path.join(basedir, 'alert_repository')
