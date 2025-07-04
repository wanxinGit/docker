# 先无持久化的状态下直接启动一个应用
docker run -d \
  --name nifi \
  -e NIFI_WEB_PROXY_HOST=172.19.20.5:18443 \
  -p 18443:8443 \
  -e SINGLE_USER_CREDENTIALS_USERNAME=admin \
  -e SINGLE_USER_CREDENTIALS_PASSWORD=Cqpgx@201811 \
  --security-opt seccomp=unconfined \
  172.19.20.5:5000/apache/nifi:2.4.0

# 将文件拷贝出来并且设置权限
docker cp nifi:/opt/nifi/nifi-current /opt/docker/storage/nifi-data
chown -R 1000:1000 /opt/docker/storage/nifi-data

# 重新以持久化方式启动应用启动
docker run -d \
  --name nifi \
  -e NIFI_WEB_PROXY_HOST=172.19.20.5:18443 \
  -p 18443:8443 \
  -e SINGLE_USER_CREDENTIALS_USERNAME=admin \
  -e SINGLE_USER_CREDENTIALS_PASSWORD=Cqpgx@201811 \
  -e TZ=Asia/Shanghai \
 -v /opt/docker/storage/nifi-data/nifi-current/conf:/opt/nifi/nifi-current/conf \
 -v /opt/docker/storage/nifi-data/nifi-current/data:/opt/nifi/nifi-current/data \
 -v /opt/docker/storage/nifi-data/nifi-current/lib:/opt/nifi/nifi-current/lib \
 -v /opt/docker/storage/nifi-data/nifi-current/flowfile_repo:/opt/nifi/nifi-current/flowfile_repo \
 -v /opt/docker/storage/nifi-data/nifi-current/content_repo:/opt/nifi/nifi-current/content_repo \
 -v /opt/docker/storage/nifi-data/nifi-current/provenance_repo:/opt/nifi/nifi-current/provenance_repo \
 -v /opt/docker/storage/nifi-data/nifi-current/database_repo:/opt/nifi/nifi-current/database_repo \
 -v /opt/docker/storage/nifi-data/nifi-current/logs:/opt/nifi/nifi-current/logs \
 --security-opt seccomp=unconfined \
 172.19.20.5:5000/apache/nifi:2.4.0

