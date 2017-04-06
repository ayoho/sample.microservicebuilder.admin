FROM websphere-liberty:microProfile
RUN installUtility install  --acceptLicense logstashCollector-1.0
COPY server.xml /config/server.xml
COPY target/microservice-admin-1.0.0-SNAPSHOT.war /config/apps/admin.war
