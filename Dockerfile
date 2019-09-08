FROM openjdk:8
MAINTAINER David Redondo Durand (davidfrd2@gmail.com)
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml pom.xml
COPY src src
RUN mvn package
EXPOSE 8080
ENTRYPOINT  ["java", "-jar", "target/TBExercise.jar"]