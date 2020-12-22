# wby-web-base docker配置
FROM carsharing/alpine-oraclejdk8-bash

MAINTAINER wby0014 (1006427479@qq.com)

ENV PROJECT_HOME /home/project
ENV TOMCAT_HOME /home/tomcat/webapps
ENV OUTPUT_HOME /output
ENV JAVA_OPTS="-Xms256m -Xmx256m -Xss1m -Xmn128m"

RUN mkdir -p "$PROJECT_HOME" && mkdir -p "$OUTPUT_HOME" && mkdir -p "$TOMCAT_HOME"

ADD wby-web-base.jar /home/project/wby-web-base.jar

WORKDIR /home/project

# 指定容器内的时区
RUN echo "Asia/Shanghai" > /etc/timezone

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar $PROJECT_HOME/wby-web-base.jar"]

EXPOSE 8182

# docker run -it -d --name wby-web-base -p8190:8190 -v /output:/output -v /home/tomcat/webapps:/home/tomcat/webapps wby-web-base:v1
