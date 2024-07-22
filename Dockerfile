# Use an official Amazon Corretto JDK 11 image as a parent image for the build stage
FROM amazoncorretto:11 as build
LABEL authors="aram"

# Install dependencies and update package lists
RUN yum update -y && yum install -y git

# Set the working directory in the container
WORKDIR /app

# Copy the entire project into the container
COPY . .

# Grant execution rights to the Gradle wrapper
RUN chmod +x gradlew

# Build the application using the bootJar task (assuming a Spring Boot application)
RUN ./gradlew build --no-daemon

# Use an official Amazon Corretto JDK 11 image for the runtime stage
FROM amazoncorretto:11

# Expose the port that your application will run on
EXPOSE 8080

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/GermanoBot-1.jar /app/GermanoBot.jar

# Set the entry point to run your application
ENTRYPOINT ["java", "-jar", "/app/GermanoBot.jar"]
