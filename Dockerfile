FROM openjdk:17-jdk-slim
WORKDIR /app

# Accept the JAR file as a build argument
ARG JAR_FILE

# Copy the dynamically provided JAR file into the container
COPY ${JAR_FILE} app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
