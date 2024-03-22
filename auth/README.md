To run auth microservice for development you need to install postgres and create a database in it called motw.  If you'd like to use your own database name just update the application.properties file with the new value

Create an application-local.properties file to keep secret information in. 

application.properties is already configured to import this file.  Add the following variables to this file

spring.datasource.username=postgres
spring.datasource.password={your postgres password}
jwt.secret={generate a secret key of 256 bits}

This project is packaged using maven 3.9.6
https://maven.apache.org/download.cgi

To run the project:
mvn spring-boot:run

To compile:
mvn clean install (To remove previously generated build and build a new one)
