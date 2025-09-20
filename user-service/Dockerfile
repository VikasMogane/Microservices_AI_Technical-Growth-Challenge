# Step 1: Use lightweight OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Step 2: Define an argument for the JAR file path
ARG JAR_FILE=target/*.jar

# Step 3: Copy the JAR into container and rename to app.jar
COPY ${JAR_FILE} app.jar

# Step 4: Expose the User Service port
EXPOSE 8080

# Step 5: Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app.jar"]