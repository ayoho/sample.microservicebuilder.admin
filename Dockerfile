FROM websphere-liberty:microProfile
RUN installUtility install  --acceptLicense logstashCollector-1.0 openidConnectClient-1.0 jsp-2.3 ssl-1.0 appSecurity-2.0
COPY server.xml /config/server.xml
COPY target/microservice-admin-1.0.0-SNAPSHOT.war /config/apps/admin.war
