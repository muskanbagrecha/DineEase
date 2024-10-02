# Step 1: Use an official Maven image to build the app
FROM maven:3.8.3-openjdk-17 AS build

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Step 4: Copy the source code into the container
COPY src ./src

COPY src/main/resources/application.properties /app/application.properties

# Step 5: Build the application and create a JAR file
RUN mvn clean package -DskipTests

# Step 6: Use an official Java runtime as the base image for running the application
FROM openjdk:17-jdk-slim

# Step 7: Set the working directory inside the container
WORKDIR /app

# Step 8: Copy the JAR file from the build stage
COPY --from=build /app/target/dineease-1.0.0.jar /app/dineease.jar

# Step 9: Expose the application port
EXPOSE 53612

# Step 10: Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "dineease.jar"]