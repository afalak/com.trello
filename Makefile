all: build

build:
	docker build -t com.trello .

run:
	docker run -p4444:4444 --rm -it com.trello