# ----- Stage 1: Build the application using Maven -----
    FROM maven:3.8.6-openjdk-11 AS build
    WORKDIR /app
    
    # Copy the Maven project file
    COPY pom.xml .
    
    # Copy configuration files into the expected source structure.
    # (Ensure your local project has these files at these locations.)
    COPY persistence.xml src/main/resources/META-INF/persistence.xml
    COPY kitchensink-quickstart-ds.xml src/main/webapp/WEB-INF/kitchensink-quickstart-ds.xml
    
    # If you have additional source code, copy the entire src directory:
    # COPY src src
    
    # Build the project and package the WAR; skipping tests for speed.
    RUN mvn clean package -DskipTests
    
    # ----- Stage 2: Deploy the WAR in JBoss/WildFly -----
    FROM jboss/wildfly:latest
    # Remove any default deployments if desired
    RUN rm -rf /opt/jboss/wildfly/standalone/deployments/*
    
    # Copy the built WAR file from the previous stage
    COPY --from=build /app/target/kitchensink.war /opt/jboss/wildfly/standalone/deployments/
    
    # Expose the default HTTP port
    EXPOSE 8080
    
    # Start WildFly in standalone mode binding to all interfaces
    CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
    