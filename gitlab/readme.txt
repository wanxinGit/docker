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

#��������Ŀ¼�ֱ��Ӧ���á���־���洢����

#20181115
����external_url���ýű�����Ҫ������ö˿ڲ�ΪĬ�ϵ�80ʱ��git·���в������˿ڵ����⡣
ͨ��������ý�docker������ӳ�����˿ں��ⲿת���˿�һ�£�������������⡣

#20181119
Ϊ���ssh�˿ڷ���gitlab�����⣬����������ӳ��˿�����