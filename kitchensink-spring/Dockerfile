# Stage 1: Build the application using Maven and Java 21
FROM maven:3.9.2-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies first
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the application (skip tests for speed)
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image using a lightweight Java 21 JRE
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the jar built in the previous stage; adjust the jar name if necessary
COPY --from=build /app/target/kitchensink.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kitchensink.jar"]
