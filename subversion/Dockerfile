FROM centos:7.3.1611
MAINTAINER wanxin <258621580@qq.com>

ENV DATA_DIR /opt/svn/repo

# Install Subversion
RUN yum install -y subversion && \
    yum clean all

# Copy config and script
COPY config /opt/svn/config
COPY script /opt/svn/script
RUN chmod +x /opt/svn/script/run.sh

EXPOSE 3690

ENTRYPOINT ["/opt/svn/script/run.sh"]
