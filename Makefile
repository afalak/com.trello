NAME=hellofalak

all: docklint
	docker build -t ${NAME} .

clean:
	docker image rm -f ${NAME}
	docker image rm -f ${DTRNAME}
	docker container prune 
	docker image prune 
	docker image ls

docklint:
	hadolint --no-fail Dockerfile

jib:
	mvn compile jib:dockerBuild

run: all
	docker run -p8080:8080 -p8888:8888 -p9999:9999 --name ${NAME} -it --rm ${NAME}
