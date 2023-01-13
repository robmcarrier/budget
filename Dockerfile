FROM openjdk:19-jdk-slim

CMD mvn package

COPY target/budget-app-*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

