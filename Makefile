all: build

build:
	docker build -t com.trello .

run:test

test: all
	docker run --rm -it com.trello

push:
	date > /tmp/msg
	git commit -a -F /tmp/msg
	git push
	git pull