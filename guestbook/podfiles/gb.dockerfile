FROM openjdk:21-jdk-slim

WORKDIR /app
COPY ../build/libs/guestbook-0.0.1-SNAPSHOT.jar guestbook-0.0.1.jar
EXPOSE 9090

CMD ["java", "-jar", "guestbook-0.0.1.jar"]