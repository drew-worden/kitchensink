# Stage 1: Build the application using Maven with Java 21
FROM maven:3.9.2-eclipse-temurin AS build
WORKDIR /app

# Copy pom.xml first and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image using a Java 21 JDK image
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the packaged jar from the build stage (update the jar name as needed)
COPY --from=build /app/target/kitchensink.jar .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kitchensink.jar"]
