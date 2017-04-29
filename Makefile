BASE_URL=http://localhost:8888/api

dependencies:
		pip install -r requirements.txt

migrate: dependencies
		python manage.py db migrate

run-server: migrate
	python manage.py runserver
