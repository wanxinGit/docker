�����ű���
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
	
# �°汾�ƺ�Ҫ��privileged��������������ʱ��Ҫ�ӣ�
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


#��������Ŀ¼�ֱ��Ӧ���á���־���洢����

#20181115
����external_url���ýű�����Ҫ������ö˿ڲ�ΪĬ�ϵ�80ʱ��git·���в������˿ڵ����⡣
ͨ��������ý�docker������ӳ�����˿ں��ⲿת���˿�һ�£�������������⡣

#20181119
Ϊ���ssh�˿ڷ���gitlab�����⣬����������ӳ��˿�����


=======================Gitlab-runner================================
# ����gitlab-runner
docker run -d --name gitlab-runner --restart always \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /opt/docker/storage/gitlab-runner/config:/etc/gitlab-runner \
  -v /opt/docker/storage/gitlab-runner/other_env:/opt/other_env \
  gitlab/gitlab-runner:v16.3.1

# ��������
docker exec -it gitlab-runner /bin/bash

# ִ��ע���
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

# �ֶ��޸������ļ���
/opt/docker/storage/gitlab-runner/config/config.toml

[[runners]]
  name = "gr01"
  url = "http://172.19.20.5:8090"
  id = 5
  token = "b-5ZAqog4bdgoLtD_rd7"
  token_obtained_at = 2024-03-21T07:00:23Z
  token_expires_at = 0001-01-01T00:00:00Z
  executor = "docker"
  #�������У�Ŀ���ǿ���ʡ��gitlab-ci.yml�еĸ�tls�йص����á�dindʹ��OverlayFS�����ӿ칹���ٶ�
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
    #�ĳɸ���һ���ģ�buildkit.toml�ļ�����һ���̳��ﴴ��������http����˽�вֿ⡢daemon.json���������˹��ھ����˽�вֿ��ַ��/certs/client������tls��Ҫ��
    volumes = ["/cache", "/var/run/docker.sock:/var/run/docker.sock", "/etc/docker/daemon.json:/etc/docker/daemon.json:ro", "/opt/docker/storage/gitlab-runner/other_env:/opt/other_env:ro"]
    pull_policy = "if-not-present"
	shm_size = 0
    extra_hosts = ["registry:172.19.20.5"]
    #[[runners.docker.services]]  #�������service�飬����gitlab-ci.yml��Ϳ���ʡ��services��
    #    name = "docker:24.0.7-dind"



# ����DOCKER_AUTH_CONFIG�����Թ���������˽�вֿ⾵��


����������ʵ���������¼���
docker restart gitlab-runner