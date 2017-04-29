import logging
import connexion

__author__ = 'frodriguez'

logging.basicConfig(level=logging.INFO)


if __name__ == '__main__':
    app = connexion.App(__name__, specification_dir='./swagger/')
    args = {'title': 'Flood alert API'}
    app.add_api('swagger.yaml', arguments=args)
    app.run(port=8888)
