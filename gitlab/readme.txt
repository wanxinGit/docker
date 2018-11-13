启动脚本：
docker run --detach \
	--hostname 172.19.20.5 \
	--publish 8443:443 --publish 8090:80 \
	--name gitlab \
	--restart always \
	--volume /opt/docker/storage/gitlab/config:/etc/gitlab \
	--volume /opt/docker/storage/gitlab/logs:/var/log/gitlab \
	--volume /opt/docker/storage/gitlab/data:/var/opt/gitlab \
	gitlab/gitlab-ce:latest


#三个挂载目录分别对应配置、日志、存储数据