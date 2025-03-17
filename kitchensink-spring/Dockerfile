# Stage 1: Build the application using Maven with OpenJDK 21
FROM maven:3.9.2-openjdk-21-slim AS build
WORKDIR /app

# Copy pom.xml first and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application (skip tests for speed)
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image using OpenJDK 21
FROM openjdk:21-slim
WORKDIR /app

# Copy the packaged jar from the build stage (adjust the jar name as needed)
COPY --from=build /app/target/kitchensink.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kitchensink.jar"]
