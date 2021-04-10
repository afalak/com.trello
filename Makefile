all: build

build:
	docker build -t com.trello .

run: all
	docker run -p4444:4444 --rm -it com.trello

push:
	msg=`date`
	git commit -am "done at ${msg}"
	git push
	git pull