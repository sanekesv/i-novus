FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir backend
ADD target/backend.jar backend/backend.jar
ADD run.sh backend/run.sh
RUN chmod +x backend/run.sh
CMD ./backend/run.sh