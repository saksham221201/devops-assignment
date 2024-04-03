FROM openjdk:17-alpine
EXPOSE 8089
ADD target/*.jar spring-boot-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]