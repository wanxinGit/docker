1����ͨhttpģʽ����
�����ű���
docker run -d \
-p 5000:5000 \
-v /opt/docker/storage/registry:/var/lib/registry \
--restart always \
--name registry \
registry:2.6.1
ע��
���������Ļ�push��pull����Ŀͻ��˶���Ҫ�������Σ��Ƚ��鷳
�������÷�ʽ��
��/etc/docker/daemon.json������������ݣ�
{ "insecure-registries":["172.19.20.5:5000"]}

�鿴�ֿ��о��������
curl http://localhost:5000/v2/_catalog

2��httpsģʽ����
1������֤�飨�����CAǩ���֤��ɺ����ⲽ����
openssl req -newkey rsa:2048 -nodes -sha256 -keyout /opt/certs/domain.key -x509 -days 365 -out /opt/certs/domain.crt
--��Ҫ�ȴ���/opt/certsĿ¼
2�������ű���
docker run -d \
-p 5000:5000 \
-v /opt/docker/storage/registry:/var/lib/registry \
--restart always \
--name registry \
-v /opt/docker/storage/registry/cert:/opt/certs \
-e REGISTRY_HTTP_TLS_CERTIFICATE=/opt/certs/domain.crt \
-e REGISTRY_HTTP_TLS_KEY=/opt/certs/domain.key \
registry:2.6.1

3�����ÿͻ���/etc/hosts
172.19.20.5 registry.cqpgx.com

4���ڿͻ��˰�װ֤��
mkdir -p /etc/docker/certs.d/registry.cqpgx.com:5000
cp /opt/certs/domain.crt /etc/docker/certs.d/registry.cqpgx.com:5000/ca.crt
systemctl restart docker

5������push��pull
docker push registry.cqpgx.com:5000/busybox:v1

6����Ȩģʽ����
����ͨ���������²���ʵ�֣���ʱû��ʵ����֤
���ù�apache�����빤�����������ļ�(������Ҫ�Ȱ�װyum install httpd)
htpasswd -c /opt/auth/htpasswd reguser
�������ű����������²�����
-v /opt/auth/htpasswd/auth:/auth \
-e "REGISTRY_AUTH=htpasswd" \
-e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
-e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
