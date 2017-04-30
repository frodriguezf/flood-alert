import logging
import connexion

from flask.ext.sqlalchemy import SQLAlchemy
from flask.ext.script import Manager, Server
from flask.ext.migrate import Migrate, MigrateCommand

from connexion.apps.flask_app import FlaskJSONEncoder

__author__ = 'frodriguez'

logging.basicConfig(level=logging.INFO)


connexionApp = connexion.App(__name__, specification_dir='./swagger/')
args = {'title': 'Flood alert API'}
connexionApp.add_api('swagger.yaml', arguments=args)

app = connexionApp.app

app.json_encoder = FlaskJSONEncoder
app.config.from_object('config')
db = SQLAlchemy(app)

migrate = Migrate(app, db)

manager = Manager(app)
manager.add_command('db', MigrateCommand)
manager.add_command("runserver", Server(port='8888'))


if __name__ == '__main__':
    manager.run()
