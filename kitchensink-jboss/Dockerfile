# Use the official JBoss WildFly image
FROM jboss/wildfly:latest

# Copy the WAR file from the Maven target directory
COPY target/kitchensink.war /opt/jboss/wildfly/standalone/deployments/

# Expose the default WildFly HTTP port
EXPOSE 8080

# Start WildFly in standalone mode, binding to all interfaces
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]