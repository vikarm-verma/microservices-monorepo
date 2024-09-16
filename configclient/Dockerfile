
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Add the jar file to the container
COPY target/configclient-0.0.1-SNAPSHOT.jar configclient-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "configclient-0.0.1-SNAPSHOT.jar"]
