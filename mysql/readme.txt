1、mysql启动脚本
docker run -d \
--name mysql \
-e "MYSQL_ROOT_PASSWORD=admin" \
-v /opt/docker/storage/mysql:/var/lib/mysql \
-v /etc/localtime:/etc/localtime:ro \
-e TZ="Asia/Shanghai" \
-p 3306:3306 \
--restart always \
mysql:5.6 \
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --max_connections=3000 --lower_case_table_names=1 --max_allowed_packet=1024m


docker run -d \
--name mysql8 \
-e "MYSQL_ROOT_PASSWORD=admin" \
-v /opt/docker/storage/mysql8:/var/lib/mysql \
-v /etc/localtime:/etc/localtime:ro \
-e TZ="Asia/Shanghai" \
-p 3307:3306 \
--restart always \
--privileged \
mysql:8.1.0 \
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --max_connections=3000 --lower_case_table_names=1 --max_allowed_packet=1024m

注：mysql8需要增加privileged参数，否则会因为权限问题启动报错。

包含
1）设置root初始密码为admin
2）设置宿主机存储路径为/opt/docker/storage/mysql
3）对外暴露端口3306
4）重启宿主机自动重启容器
5）数据库最大连接数3000
5）表名大小写不敏感



nohup docker?save?-o?/opt/docker/dmp_img20230326.tar?192.168.15.143:5000/dmp:1.2.5 > docker_save_console.log > 2>&1 &
docker save 192.168.15.143:5000/dmp:1.2.5 > /opt/docker/dmp_img20230326.tar


# 看一下存储位置
docker info
#  Docker Root Dir: /data/docker/lib/docker
# 改一下存储位置
nano /etc/docker/daemon.json
# 重新加载
systemctl daemon-reload
systemctl restart docker


Docker Root Dir: /var/lib/docker



{
"data-root": "/opt/docker-root-dir"
}