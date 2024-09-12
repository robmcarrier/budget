FROM openjdk:19-jdk-slim

CMD mvn package

COPY target/budget-app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

