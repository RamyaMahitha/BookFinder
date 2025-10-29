# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project files
COPY . .

# Give execute permission to mvnw
RUN chmod +x mvnw

# Package the application (skip tests to save build time)
RUN ./mvnw clean package -DskipTests

# Expose port 8080 (default Spring Boot port)
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/book-finder-0.0.1-SNAPSHOT.jar"]
