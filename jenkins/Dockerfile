FROM jenkins:2.46.1
MAINTAINER wanxin <258621580@qq.com>


# Copy init config and script
COPY config /opt/jenkins/config
COPY script /opt/jenkins/script
# RUN chmod +x /opt/jenkins/script/run.sh

EXPOSE 8080
EXPOSE 50000

# ENTRYPOINT ["/opt/jenkins/script/run.sh"]