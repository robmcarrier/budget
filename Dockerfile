FROM maven:3.9.9-eclipse-temurin-21-alpine

COPY ./src/ /app/src
COPY ./pom.xml /app/pom.xml
WORKDIR /app

RUN mvn verify package

COPY ./target/budget-app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

