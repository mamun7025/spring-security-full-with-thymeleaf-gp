FROM openjdk:8
EXPOSE 8181
ADD target/docker-spring-security.jar docker-spring-security.jar
ENTRYPOINT ["java", "jar", "/docker-spring-security.jar"]