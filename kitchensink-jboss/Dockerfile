# Use an official Maven image with JDK to build the project
FROM maven:3.8.6-openjdk-11 AS builder

# Set working directory
WORKDIR /app

# Copy the entire project (source code, resources, and pom.xml)
COPY . .

# Build the project with Maven, skipping tests for faster build
RUN mvn clean package -DskipTests

# Use WildFly image for runtime
FROM jboss/wildfly:latest

# Copy the built WAR file from the builder stage
COPY --from=builder /app/target/kitchensink.war /opt/jboss/wildfly/standalone/deployments/

# Copy the custom WildFly configuration file from the resources directory
COPY --from=builder /app/src/main/resources/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

# Copy the SQL script (import.sql) from the resources directory so that it's available on the classpath
COPY --from=builder /app/src/main/resources/import.sql /opt/jboss/wildfly/standalone/configuration/import.sql

# Expose WildFly's default HTTP port
EXPOSE 8080

# Start WildFly with the custom standalone.xml configuration, binding to all interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-c", "standalone.xml"]