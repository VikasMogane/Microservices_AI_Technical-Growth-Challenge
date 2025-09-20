# Use OpenJDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file (replace with your actual jar name if different)
COPY target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

# Expose Eureka default port
EXPOSE 8761

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
