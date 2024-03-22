# Use the openjdk image as the base image
FROM openjdk:21-slim

# Set the working directory within the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application using Maven
RUN mvn clean install

# Display the contents of the target directory (for debugging purposes)
RUN ls -l target

# Expose port 8080
EXPOSE 8080

# Run the application using the full path to the JAR file
CMD ["java", "-jar", "/app/target/contactapi-0.0.1-SNAPSHOT.jar"]