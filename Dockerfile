FROM tomcat:10.1-jdk21

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY . /usr/local/tomcat/webapps/ROOT

RUN mkdir -p /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

RUN find /usr/local/tomcat/webapps/ROOT/java -name "*.java" -print0 | \
    xargs -0 javac -cp "/usr/local/tomcat/lib/*" \
    -d /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

EXPOSE 8080
