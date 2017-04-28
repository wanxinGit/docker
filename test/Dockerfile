FROM hub.c.163.com/public/centos:7.2.1511
MAINTAINER wanxin <258621580@qq.com>


# Copy init config and script
COPY script /opt/script
RUN chmod +x /opt/script/*.sh

CMD ["/opt/script/run.sh"]
# ENTRYPOINT ["/opt/script/run2.sh"]