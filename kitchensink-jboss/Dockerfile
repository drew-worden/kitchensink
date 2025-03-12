# Use an official Maven image with JDK to build the project
FROM maven:3.8.6-openjdk-11 AS builder

# Set working directory
WORKDIR /app

# Copy the entire project (source code and pom.xml)
COPY . .

# Build the project with Maven, skipping tests for faster build
RUN mvn clean package -DskipTests

# Use WildFly image for runtime
FROM jboss/wildfly:latest

# Copy the built WAR file from the builder stage
COPY --from=builder /app/target/kitchensink.war /opt/jboss/wildfly/standalone/deployments/

# Copy the custom standalone.xml file (adjust path if different)
COPY --from=builder /app/src/main/resources/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

# Expose WildFly's default HTTP port
EXPOSE 8080

# Start WildFly with the custom standalone.xml
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-c", "standalone.xml"]