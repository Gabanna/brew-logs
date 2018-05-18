FROM jboss/wildfly:12.0.0.Final

ADD target/server-java.war /opt/jboss/wildfly/standalone/deployments/
ADD standalone-brewlogs.xml /opt/wildfly/standalone/configuration
ADD modules /opt/wildfly/modules

CMD ["/opt/wildfly/bin/standalone.sh", "-c", "standalone-brewlogs.xml", "-b", "0.0..0", "-bmanagement", "0.0.0.0"]0