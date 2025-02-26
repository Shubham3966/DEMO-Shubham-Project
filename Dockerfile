FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --chown=appuser:appuser target/MCM-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]