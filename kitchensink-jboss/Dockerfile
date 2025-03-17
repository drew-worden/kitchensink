FROM quay.io/wildfly/wildfly:latest-jdk17

LABEL maintainer="Your Name <your.email@example.com>"

ENV WILDFLY_HOME=/opt/wildfly \
    DEPLOYMENT_DIR=/opt/wildfly/standalone/deployments

USER root

COPY --chown=wildfly:wildfly target/kitchensink.war ${DEPLOYMENT_DIR}/
COPY --chown=wildfly:wildfly src/main/webapp/WEB-INF/kitchensink-quickstart-ds.xml ${WILDFLY_HOME}/standalone/configuration/
COPY --chown=wildfly:wildfly seed.sql ${WILDFLY_HOME}/standalone/data/

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

EXPOSE 8080 9990

USER wildfly

WORKDIR ${WILDFLY_HOME}

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]