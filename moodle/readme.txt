1�����ݿ����mysql5.6
2��moodle�汾����3.3�ģ����°汾��3.4��������Ҫ�Լ�build
3������Ϊ�˲���ѧУmod�汾��moodle��moodle��moodledataĿ¼���ǹ��صı��ش��̣�ʵ�����Ӧ��ֻ��Ҫ����moodledataĿ¼


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

#�þ�����е�moodle����汾���Ƽ���
docker run -d \
-P --name moodle \
--link DB:DB \
-e MOODLE_URL=http://172.16.14.202:8090 \
-p 8090:80 \
-v /opt/docker/storage/moodle/moodledata:/var/moodledata \
jauer/moodle


#����汾�Խ�
docker run -d \
-P --name moodle \
--link DB:DB \
-e MOODLE_URL=http://172.16.14.202:8090 \
-p 8090:80 \
-v /opt/docker/storage/moodle/moodle:/var/www/html \
-v /opt/docker/storage/moodle/moodledata:/var/moodledata \
jauer/moodle