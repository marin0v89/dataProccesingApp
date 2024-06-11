# Use a Java runtime as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Java application JAR file into the container
COPY target/DataProcessingApp-1.0.0.jar /app/DataProcessingApp-1.0.0.jar

# Specify the command to run your application
CMD ["java", "-jar", "DataProcessingApp-1.0.0.jar"]
