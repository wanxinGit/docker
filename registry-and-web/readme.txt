1、创建对应数据目录（包括配置文件）
./registry

2、执行脚本生成密钥对
yum -y install httpd-tools
openssl req -new -newkey rsa:4096 -days 365 \
                -subj "/CN=172.19.20.5" \
                -nodes -x509  \
                -keyout /opt/docker/storage/registry/conf/registry-web/auth.key \
                -out /opt/docker/storage/registry/conf/registry/auth.cert
				
3、启动registry容器
docker run -d \
--restart=always \
-v /opt/docker/storage/registry/conf/registry/config.yml:/etc/docker/registry/config.yml:ro \
-v /opt/docker/storage/registry/conf/registry/auth.cert:/etc/docker/registry/auth.cert:ro \
-v /opt/docker/storage/registry/data:/var/lib/registry \
-v /etc/localtime:/etc/localtime:ro \
-e TZ="Asia/Shanghai" \
-e "REGISTRY_STORAGE_DELETE_ENABLED=true" \
-p 5000:5000 \
--name registry-srv \
registry:2.6.1

4、启动registry-web容器
docker run -it -d \
-p 8000:8080 \
--restart=always \
-v /opt/docker/storage/registry/conf/registry-web/config.yml:/conf/config.yml:ro \
-v /opt/docker/storage/registry/conf/registry-web/auth.key:/conf/auth.key \
-v /opt/docker/storage/registry/db:/data \
-v /etc/localtime:/etc/localtime:ro \
-e TZ="Asia/Shanghai" \
--name registry-web \
hyper/docker-registry-web


5、验证
1）页面打开http://172.19.20.5:8000/，默认密码admin/admin登录访问
2）测试登录并push镜像
docker login 172.19.20.5:5000 -u admin -p admin
docker push 172.19.20.5:5000/registry