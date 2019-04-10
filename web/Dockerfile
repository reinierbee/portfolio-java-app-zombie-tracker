FROM tomcat:8-jre8

RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./ZombieService-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war


CMD ["catalina.sh", "run"]