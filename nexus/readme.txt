1、nexus3启动脚本
docker run -d -p 8082:8081 \
--name nexus \
-v /opt/docker/storage/nexus-data:/nexus-data \
--restart always \
sonatype/nexus3

1）默认账号密码admin/admin123
2）需要设置/opt/docker/storage/nexus-data目录的其他用户权限