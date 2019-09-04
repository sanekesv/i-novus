FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/sanekesv
ADD target/i-niovus-test.war i-novus-test.war
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh