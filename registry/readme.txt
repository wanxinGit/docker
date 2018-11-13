1、普通http模式启动
启动脚本：
docker run -d \
-p 5000:5000 \
-v /opt/docker/storage/registry:/var/lib/registry \
--restart always \
--name registry \
registry:2.6.1
注：
这样启动的话push和pull镜像的客户端都需要配置信任，比较麻烦
具体配置方式：
在/etc/docker/daemon.json中添加如下内容：
{ "insecure-registries":["172.19.20.5:5000"]}

查看仓库中镜像情况：
curl http://localhost:5000/v2/_catalog

2、https模式启动
1）创建证书（如果有CA签署的证书可忽略这步）：
openssl req -newkey rsa:2048 -nodes -sha256 -keyout /opt/certs/domain.key -x509 -days 365 -out /opt/certs/domain.crt
--需要先创建/opt/certs目录
2）启动脚本：
docker run -d \
-p 5000:5000 \
-v /opt/docker/storage/registry:/var/lib/registry \
--restart always \
--name registry \
-v /opt/docker/storage/registry/cert:/opt/certs \
-e REGISTRY_HTTP_TLS_CERTIFICATE=/opt/certs/domain.crt \
-e REGISTRY_HTTP_TLS_KEY=/opt/certs/domain.key \
registry:2.6.1

3）配置客户端/etc/hosts
172.19.20.5 registry.cqpgx.com

4）在客户端安装证书
mkdir -p /etc/docker/certs.d/registry.cqpgx.com:5000
cp /opt/certs/domain.crt /etc/docker/certs.d/registry.cqpgx.com:5000/ca.crt
systemctl restart docker

5）正常push和pull
docker push registry.cqpgx.com:5000/busybox:v1

6）鉴权模式运行
可以通过增加如下参数实现，暂时没有实际验证
·用过apache的密码工具生成密码文件(可能需要先安装yum install httpd)
htpasswd -c /opt/auth/htpasswd reguser
·启动脚本中增加如下参数：
-v /opt/auth/htpasswd/auth:/auth \
-e "REGISTRY_AUTH=htpasswd" \
-e "REGISTRY_AUTH_HTPASSWD_REALM=Registry Realm" \
-e REGISTRY_AUTH_HTPASSWD_PATH=/auth/htpasswd \
