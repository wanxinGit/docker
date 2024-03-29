启动脚本：
docker run --detach \
	--hostname 172.19.20.5 \
	-e GITLAB_OMNIBUS_CONFIG="external_url 'http://172.19.20.5:8090';gitlab_rails['gitlab_shell_ssh_port'] = 2223; " \
	--publish 8443:443 \
	--publish 8090:8090 \
	--publish 2223:22 \
	--name gitlab \
	--restart always \
	--volume /opt/docker/storage/gitlab/config:/etc/gitlab \
	--volume /opt/docker/storage/gitlab/logs:/var/log/gitlab \
	--volume /opt/docker/storage/gitlab/data:/var/opt/gitlab \
	gitlab/gitlab-ce:latest
	
# 新版本似乎要加privileged参数（至少升级时需要加）
docker run --detach \
	--hostname 172.19.20.5 \
	-e GITLAB_OMNIBUS_CONFIG="external_url 'http://172.19.20.5:8090';gitlab_rails['gitlab_shell_ssh_port'] = 2223; " \
	--publish 8443:443 \
	--publish 8090:8090 \
	--publish 2223:22 \
	--name gitlab \
	--privileged \
	--restart always \
	--volume /opt/docker/storage/gitlab/config:/etc/gitlab \
	--volume /opt/docker/storage/gitlab/logs:/var/log/gitlab \
	--volume /opt/docker/storage/gitlab/data:/var/opt/gitlab \
	gitlab/gitlab-ce:16.3.7-ce.0


#三个挂载目录分别对应配置、日志、存储数据

#20181115
新增external_url配置脚本，主要解决配置端口不为默认的80时的git路径中不包含端口的问题。
通过这个配置将docker容器内映射服务端口和外部转发端口一致，将不会出现问题。

#20181119
为解决ssh端口访问gitlab的问题，新增参数和映射端口配置


=======================Gitlab-runner================================
# 启动gitlab-runner
docker run -d --name gitlab-runner --restart always \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /opt/docker/storage/gitlab-runner/config:/etc/gitlab-runner \
  gitlab/gitlab-runner:v16.3.1

# 进入容器
docker exec -it gitlab-runner

# 执行注册绑定
gitlab-runner register \
--non-interactive \
--url "http://172.19.20.5:8090" \
--registration-token "AaLVzw9oaxihxLYdKXVS" \
--executor "docker" \
--docker-image maven:latest \
--description "gr01" \
--tag-list "runner-01" \
--run-untagged="true" \
--locked="false" \
--access-level="not_protected"

gitlab-runner register \
--non-interactive \
--url "http://172.19.20.5:8090" \
--registration-token "AaLVzw9oaxihxLYdKXVS" \
--executor "docker" \
--docker-image maven:latest \
--description "gr02" \
--tag-list "runner-02" \
--run-untagged="true" \
--locked="false" \
--access-level="not_protected"