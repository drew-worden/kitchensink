# Use official JBoss EAP 8.0 base image from Red Hat
FROM registry.redhat.io/jboss-eap-8/eap8-openjdk17-openshift-rhel8:latest

# Set maintainer label
LABEL maintainer="Your Name <your.email@example.com>"

# Environment variables
ENV EAP_HOME=/opt/eap \
    DEPLOYMENT_DIR=/opt/eap/standalone/deployments

# Switch to admin user for EAP configuration
USER root

# Copy the Maven-built WAR file
# Assuming the war file is built as kitchensink.war in target directory
COPY --chown=jboss:jboss target/kitchensink.war ${DEPLOYMENT_DIR}/

# Copy datasource configuration (assuming it exists in src/main/webapp/WEB-INF/)
COPY --chown=jboss:jboss src/main/webapp/WEB-INF/kitchensink-quickstart-ds.xml ${EAP_HOME}/standalone/configuration/

# Copy seed data SQL file (assuming it's named seed.sql)
COPY --chown=jboss:jboss seed.sql ${EAP_HOME}/standalone/data/

# Configure JBoss EAP
RUN echo "JAVA_OPTS=\"\$JAVA_OPTS -Djboss.server.data.dir=/opt/eap/standalone/data\"" >> ${EAP_HOME}/bin/standalone.conf && \
    # Add datasource to standalone.xml
    ${EAP_HOME}/bin/jboss-cli.sh --command="embed-server, \
    /subsystem=datasources/data-source=KitchensinkQuickstartDS:add( \
    jndi-name=java:jboss/datasources/KitchensinkQuickstartDS, \
    driver-name=h2, \
    connection-url=jdbc:h2:mem:kitchensink;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE, \
    user-name=sa, \
    password=sa, \
    enabled=true)" && \
    # Configure H2 driver if not already present
    ${EAP_HOME}/bin/jboss-cli.sh --command="embed-server, \
    /subsystem=datasources/jdbc-driver=h2:add( \
    driver-name=h2, \
    driver-module-name=org.h2, \
    driver-class-name=org.h2.Driver)" && \
    # Set deployment scanner
    ${EAP_HOME}/bin/jboss-cli.sh --command="embed-server, \
    /subsystem=deployment-scanner/scanner=default:write-attribute(name=scan-interval,value=5000)"

# Expose default JBoss EAP ports
EXPOSE 8080 9990

# Switch back to jboss user
USER jboss

# Set working directory
WORKDIR ${EAP_HOME}

# Start JBoss EAP
CMD ["/opt/eap/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]