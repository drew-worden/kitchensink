# Stage 1: Build the application with Maven
FROM openjdk:17-jdk-slim AS builder

# Set working directory
WORKDIR /app

# Copy the entire project (POM and source files)
COPY pom.xml .
COPY src/ src/

# Copy the seed.sql file (for later use in the runtime stage)
COPY seed.sql .

# Build the application, skipping tests for faster build
RUN mvn clean package -DskipTests

# Stage 2: Deploy to WildFly
FROM quay.io/wildfly/wildfly:latest-jdk17

# Set maintainer label
LABEL maintainer="Your Name <your.email@example.com>"

# Environment variables
ENV WILDFLY_HOME=/opt/wildfly \
    DEPLOYMENT_DIR=/opt/wildfly/standalone/deployments

# Switch to root for configuration
USER root

# Copy the built WAR file from the builder stage
COPY --from=builder --chown=wildfly:wildfly /app/target/kitchensink.war ${DEPLOYMENT_DIR}/

# Copy datasource configuration from builder stage (assuming it’s in src/main/webapp/WEB-INF/)
COPY --from=builder --chown=wildfly:wildfly /app/src/main/webapp/WEB-INF/kitchensink-quickstart-ds.xml ${WILDFLY_HOME}/standalone/configuration/

# Copy seed data SQL file
COPY --from=builder --chown=wildfly:wildfly /app/seed.sql ${WILDFLY_HOME}/standalone/data/

# Configure WildFly
RUN echo "JAVA_OPTS=\"\$JAVA_OPTS -Djboss.server.data.dir=/opt/wildfly/standalone/data\"" >> ${WILDFLY_HOME}/bin/standalone.conf && \
    ${WILDFLY_HOME}/bin/jboss-cli.sh --command="embed-server, \
    /subsystem=datasources/data-source=KitchensinkQuickstartDS:add( \
    jndi-name=java:jboss/datasources/KitchensinkQuickstartDS, \
    driver-name=h2, \
    connection-url=jdbc:h2:mem:kitchensink;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE, \
    user-name=sa, \
    password=sa, \
    enabled=true)" && \
    ${WILDFLY_HOME}/bin/jboss-cli.sh --command="embed-server, \
    /subsystem=datasources/jdbc-driver=h2:add( \
    driver-name=h2, \
    driver-module-name=org.h2, \
    driver-class-name=org.h2.Driver)"

# Expose WildFly ports
EXPOSE 8080 9990

# Switch back to wildfly user
USER wildfly

# Set working directory
WORKDIR ${WILDFLY_HOME}

# Start WildFly
CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]