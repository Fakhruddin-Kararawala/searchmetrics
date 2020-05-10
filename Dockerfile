FROM java:8-jre-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "app.jar"]