import logging
import connexion

from flask_script import Manager, Server
from flask_migrate import Migrate, MigrateCommand

from connexion.apps.flask_app import FlaskJSONEncoder

from models.alert import db
import seed

__author__ = 'frodriguez'

logging.basicConfig(level=logging.INFO)


connexionApp = connexion.App(__name__, specification_dir='./swagger/')
args = {'title': 'Flood alert API'}
connexionApp.add_api('swagger.yaml', arguments=args)

app = connexionApp.app

app.json_encoder = FlaskJSONEncoder
app.config.from_object('config')

# Initialize and create the database.
db.init_app(app)
db.create_all(app=app)

migrate = Migrate(app, db)

manager = Manager(app)
manager.add_command('db', MigrateCommand)
manager.add_command('runserver', Server(port='8888'))
manager.add_command('seed', seed.Seed())


if __name__ == '__main__':
    manager.run()
