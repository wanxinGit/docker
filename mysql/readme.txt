1��mysql�����ű�
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

����
1������root��ʼ����Ϊadmin
2�������������洢·��Ϊ/opt/docker/storage/mysql
3�����Ⱪ¶�˿�3306
4�������������Զ���������
5�����ݿ����������3000
5��������Сд������