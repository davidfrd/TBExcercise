FROM openjdk:8
ADD target/TBExercise.jar TBExercise.jar
EXPOSE 8080
ENTRYPOINT  ["java", "-jar", "TBExercise.jar"]