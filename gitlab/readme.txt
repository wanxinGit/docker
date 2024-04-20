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
  -v /opt/docker/storage/gitlab-runner/other_env:/opt/other_env \
  gitlab/gitlab-runner:v16.3.1

# 进入容器
docker exec -it gitlab-runner /bin/bash

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

# 手动修改配置文件：
/opt/docker/storage/gitlab-runner/config/config.toml

[[runners]]
  name = "gr01"
  url = "http://172.19.20.5:8090"
  id = 5
  token = "b-5ZAqog4bdgoLtD_rd7"
  token_obtained_at = 2024-03-21T07:00:23Z
  token_expires_at = 0001-01-01T00:00:00Z
  executor = "docker"
  #增加这行，目的是可以省略gitlab-ci.yml中的跟tls有关的配置、dind使用OverlayFS驱动加快构建速度
  environment = ['DOCKER_DRIVER=overlay2', 'DOCKER_TLS_CERTDIR=/certs', 'DOCKER_AUTH_CONFIG={"auths": {"172.19.20.5:5000": {"auth": "YWRtaW46YWRtaW4="}}}']
  [runners.cache]
    MaxUploadedArchiveSize = 0
  [runners.docker]
    tls_verify = false
    image = "172.19.20.5:5000/docker:v1"
    privileged = true
    disable_entrypoint_overwrite = false
    oom_kill_disable = false
    disable_cache = false
    #改成跟我一样的：buildkit.toml文件是上一个教程里创建的用来http访问私有仓库、daemon.json里是配置了国内镜像和私有仓库地址、/certs/client是配置tls需要的
    volumes = ["/cache", "/var/run/docker.sock:/var/run/docker.sock", "/etc/docker/daemon.json:/etc/docker/daemon.json:ro", "/opt/docker/storage/gitlab-runner/other_env:/opt/other_env:ro"]
    pull_policy = "if-not-present"
	shm_size = 0
    extra_hosts = ["registry:172.19.20.5"]
    #[[runners.docker.services]]  #增加这个service块，这样gitlab-ci.yml里就可以省略services了
    #    name = "docker:24.0.7-dind"



# 增加DOCKER_AUTH_CONFIG，可以构建是下载私有仓库镜像


重启容器来实现配置重新加载
docker restart gitlab-runner