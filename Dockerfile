FROM openjdk:8-alpine
ADD ./target/meetingMinutes-demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]