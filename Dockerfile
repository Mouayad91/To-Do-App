FROM openjdk:22-jdk
ADD target/todoapp.jar todoapp.jar
ENTRYPOINT ["java", "-jar", "/todoapp.jar"]
