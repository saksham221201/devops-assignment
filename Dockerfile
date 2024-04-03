FROM openjdk:17-alpine
EXPOSE 8089
ADD target/customer-management-service.jar customer-management-service.jar
ENTRYPOINT ["java", "-jar", "/customer-management-service.jar"]