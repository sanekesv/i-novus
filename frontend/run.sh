#!/bin/sh
cd frontend

echo "********************************************************"
echo "Waiting for the eureka server to start on port $EUREKASERVER_PORT"
echo "********************************************************"
while ! nc -z eurekaserver $EUREKASERVER_PORT; do sleep 3; done
echo "******* Eureka Server has started"

echo "********************************************************"
echo "Waiting for the backend server to start on port $BACKENDSERVER_PORT"
echo "********************************************************"
while ! nc -z backendserver $BACKENDSERVER_PORT; do sleep 3; done
echo "******* Worker Server has started"

echo "********************************************************"
echo "Starting Frontend Module..."
echo "********************************************************"
java -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI -jar i-novus-test.jar