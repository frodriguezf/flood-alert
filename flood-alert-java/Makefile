SHELL := /bin/bash

tomcat-local:
	mvn tomcat7:run-war

NPM := $(shell command -v npm)
NODE := $(shell command -v node)

swagger-editor:
ifndef NPM
    $(error "npm is not available please install npm")
endif
ifndef NODE
    $(error "node is not available please install nodejs-legacy")
endif
	npm install serve-swagger-editor
	node swagger-editor/bin/swagger-editor.js
.PHONY: swagger-editor
