BASE_URL=http://localhost:8888/api

dependencies:
	pip install -r requirements.txt

run-server: dependencies
	python app.py
