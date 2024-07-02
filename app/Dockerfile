FROM openjdk:8-alpine
ADD target/spring-postgres-docker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#FROM maven:3.6.1-jdk-8-slim AS build
#RUN mkdir -p /workspace
#WORKDIR /workspace
#COPY pom.xml /workspace
#COPY src /workspace/src
#RUN mvn -f pom.xml clean package
#
#FROM openjdk:8-alpine
#COPY --from=build /workspace/target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]
##FROM tomcat:jre11-slim
#FROM tomcat:9.0-alpine
#LABEL maintainer="Will.Smith@example.com"
##RUN apt-get update && apt-get -y upgrade
#RUN apk update   && apk upgrade
##Remove defualt ROOT.war and replace it with app
#RUN rm -rf /usr/local/tomcat/webapps/*
#COPY meetingMinutes-demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/meeting.war
#CMD ["catalina.sh", "run"]
#EXPOSE 8080