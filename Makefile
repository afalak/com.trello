NAME=com.trello

all: 
	docker build -t ${NAME} .

clean:
	docker image rm -f ${NAME}
	docker container prune 
	docker image prune 
	docker image ls
