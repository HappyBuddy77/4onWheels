FROM eclipse-temurin:24.0.1_9-jdk-ubi9-minimal

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar


EXPOSE 8080
# EXPOSE 5000 # for elastic beanstalk

ENTRYPOINT ["java", "-jar", "app.jar"]