# Base image to use, this must be set as the first line
FROM ubuntu:14.04

# Maintainer: docker_user <docker_user at email.com> (@docker_user)
MAINTAINER wanxin <wanxin@yufex.com>


# build runtime environment
ENV BASE_ENV /opt/base_env
ENV JAVA_HOME ${BASE_ENV}/jdk1.7.0_79
ENV JRE_HOME ${JAVA_HOME}/jre
ENV CLASS_PATH .:${JAVA_HOME}/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
ENV PATH $PATH:${JAVA_HOME}/bin:$JRE_HOME/bin
RUN export JAVA_HOME JRE_HOME CLASS_PATH PATH

# build tomcat server environment
COPY env /opt/server
RUN apt-get update && apt-get install -y zip
RUN unzip /opt/server/apache-tomcat-7.0.77.zip
ENV TOMCAT_HOME /opt/server/apache-tomcat-7.0.77
RUN chmod 777 ${TOMCAT_HOME}/bin/*.sh

# put web package
RUN rm -rf ${TOMCAT_HOME}/webapps/*
COPY war ${TOMCAT_HOME}/webapps

# Commands when creating a new container
EXPOSE 8080
ENTRYPOINT ${TOMCAT_HOME}/bin/startup.sh && tail -F ${TOMCAT_HOME}/logs/catalina.out