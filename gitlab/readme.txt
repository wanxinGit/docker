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
  gitlab/gitlab-runner:v16.3.1

# ��������
docker exec -it gitlab-runner

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