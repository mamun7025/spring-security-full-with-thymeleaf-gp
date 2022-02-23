FROM tomcat:9
RUN rm -rf /usr/local/tomcat/webapps/*
EXPOSE 8181
ARG WAR_FILE=build/libs/springsecurity_thymeleaf_gp-0.0.1-SNAPSHOT.war
COPY ${WAR_FILE} /usr/local/tomcat/webapps/springsecurity_thymeleaf_gp.war
CMD ["catalina.sh","run"]