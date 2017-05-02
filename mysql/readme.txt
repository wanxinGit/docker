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
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --max_connections=3000 --lower_case_table_names=1

包含
1）设置root初始密码为admin
2）设置宿主机存储路径为/opt/docker/storage/mysql
3）对外暴露端口3306
4）重启宿主机自动重启容器
5）数据库最大连接数3000
5）表名大小写不敏感