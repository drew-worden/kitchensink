FROM quay.io/wildfly/wildfly:latest

COPY ./standalone.xml /opt/jboss/wildfly/standalone/configuration/
COPY ./kitchensink.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]