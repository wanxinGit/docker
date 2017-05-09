# Base image to use, this must be set as the first line
FROM ubuntu:14.04

# Maintainer: docker_user <docker_user at email.com> (@docker_user)
MAINTAINER wanxin <wanxin@yufex.com>

# build runtime environment
ENV BASE_ENV /opt/base_env
ENV JAVA_HOME ${BASE_ENV}/jdk1.7.0_79
ENV JRE_HOME ${BASE_ENV}/jdk1.7.0_79/jre
RUN echo "#set java environment" >> /etc/profile && \
	echo "export JAVA_HOME=${JAVA_HOME}" >> /etc/profile && \
	echo "export JRE_HOME=${JAVA_HOME}/jre" >> /etc/profile && \
	echo "export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib" >> /etc/profile && \
	echo "export PATH=${JAVA_HOME}/bin:$PATH" >> /etc/profile

# build tomcat server environment
COPY env /opt/server
RUN apt-get update && apt-get install -y zip
RUN unzip /opt/server/apache-tomcat-7.0.77.zip -d /opt/server
ENV TOMCAT_HOME /opt/server/apache-tomcat-7.0.77
RUN echo ${TOMCAT_HOME}
RUN chmod 777 ${TOMCAT_HOME}/bin/*.sh

# put web package(将这个步骤移到-v挂在卷来处理)
RUN rm -rf ${TOMCAT_HOME}/webapps/*
COPY war ${TOMCAT_HOME}/webapps

# Commands when creating a new container
EXPOSE 8080
ENTRYPOINT ${TOMCAT_HOME}/bin/startup.sh && tail -F ${TOMCAT_HOME}/logs/catalina.out