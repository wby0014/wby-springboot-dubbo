# wby-monitor docker配置
FROM frolvlad/alpine-oraclejdk8

MAINTAINER wby (1006427479@qq.com)

ENV PROJECT_HOME /home/project
ENV TOMCAT_HOME /home/tomcat/webapps
ENV OUTPUT_HOME /output
ENV JAVA_OPTS="-Xms256m -Xmx256m -Xss1m -Xmn128m"

RUN mkdir -p "$PROJECT_HOME" && mkdir -p "$OUTPUT_HOME" && mkdir -p "$TOMCAT_HOME"

ADD wby-monitor.jar $PROJECT_HOME/wby-monitor.jar

WORKDIR /home/project

# 指定容器内的时区
RUN echo "Asia/Shanghai" > /etc/timezone

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $PROJECT_HOME/wby-monitor.jar"]

EXPOSE 8180

# docker run -it -d --name wby-monitor -p8180:8180 -v /output:/output wby-monitor:v1
