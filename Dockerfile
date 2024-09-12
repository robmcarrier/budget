FROM maven:3.9.9-eclipse-temurin-21-alpine

CMD mvn package

COPY ./target/budget-app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

