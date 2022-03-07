1、nexus3启动脚本
docker run -d \
-p 8082:8081 \
-p 8084:8084 \
-p 8085:8085 \
-p 8086:8086 \
--name nexus \
-v /opt/docker/storage/nexus-data:/nexus-data \
--restart always \
sonatype/nexus3

1）默认账号密码admin/admin123
2）需要设置/opt/docker/storage/nexus-data目录的其他用户权限
3）8084、8085、8086端口为docker镜像仓库所用(8084为host仓库，8085为proxy仓库，8086为整合了前两个的group仓库)