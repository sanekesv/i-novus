FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir eureka
ADD target/eureka.jar eureka/eureka.jar
ADD run.sh eureka/run.sh
RUN chmod +x eureka/run.sh
CMD ./eureka/run.sh