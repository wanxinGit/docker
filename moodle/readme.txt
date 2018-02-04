1、数据库采用mysql5.6
2、moodle版本采用3.3的，最新版本是3.4，但是需要自己build
3、这里为了采用学校mod版本的moodle，moodle和moodledata目录都是挂载的本地磁盘，实际情况应该只需要挂载moodledata目录


docker run -d \
--name DB \
-p 3306:3306 \
-v /etc/localtime:/etc/localtime:ro \
-v /opt/docker/storage/mysql:/var/lib/mysql \
-e TZ="Asia/Shanghai" \
-e MYSQL_DATABASE=moodle \
-e MYSQL_ROOT_PASSWORD=moodle \
-e MYSQL_USER=moodle \
-e MYSQL_PASSWORD=moodle \
mysql:5.6 \
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci \
--innodb_large_prefix=1 --innodb_file_format=Barracuda \
--max_connections=3000 --lower_case_table_names=1 \
--max_allowed_packet=1024m

#用镜像包中的moodle软件版本（推荐）
docker run -d \
-P --name moodle \
--link DB:DB \
-e MOODLE_URL=http://172.16.14.202:8090 \
-p 8090:80 \
-v /opt/docker/storage/moodle/moodledata:/var/moodledata \
jauer/moodle


#软件版本自建
docker run -d \
-P --name moodle \
--link DB:DB \
-e MOODLE_URL=http://172.16.14.202:8090 \
-p 8090:80 \
-v /opt/docker/storage/moodle/moodle:/var/www/html \
-v /opt/docker/storage/moodle/moodledata:/var/moodledata \
jauer/moodle