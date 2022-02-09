FROM openjdk:16.0.2
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/microservice-0.0.1-SNAPSHOT.jar"]