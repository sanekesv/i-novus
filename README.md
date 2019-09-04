# Реализация тестового задания для компании Ай-Новус
##### задача : Разработать web-приложение, позволяющее загружать xml-файл на сервер и выводить в формате json.

Реализовано на языке java, с использованием Spring Boot

### Запуск приложения

- Запуск на сервере приложений tomcat

```
Выполнить команду 
mvn clean && mvn install -P tomcat-build
Скопировать target/ROOT.war в %TOMCAT_HOME%/webapps/
``` 

- Запуск на Docker

```
Запустить скрипт 
./start.sh
```
