FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir frontend
ADD target/i-novus-test.jar frontend/i-novus-test.jar
ADD run.sh frontend/run.sh
RUN chmod +x frontend/run.sh
CMD ./frontend/run.sh