FROM quay.io/wildfly/wildfly:latest

# Copy custom WildFly configuration
COPY ./standalone.xml /opt/jboss/wildfly/standalone/configuration/

# Copy your application (with H2 bundled)
COPY ./kitchensink-quickstart.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]