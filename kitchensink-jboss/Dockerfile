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

# Copy the CLI script for seed data
COPY --from=builder /app/seed-data.cli /opt/jboss/wildfly/

# Run the CLI script to load seed data
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/opt/jboss/wildfly/seed-data.cli

# Expose WildFly's default HTTP port
EXPOSE 8080

# Start WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]