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

ע��mysql8��Ҫ����privileged�������������ΪȨ��������������

����
1������root��ʼ����Ϊadmin
2�������������洢·��Ϊ/opt/docker/storage/mysql
3�����Ⱪ¶�˿�3306
4�������������Զ���������
5�����ݿ����������3000
5��������Сд������



nohup docker?save?-o?/opt/docker/dmp_img20230326.tar?192.168.15.143:5000/dmp:1.2.5 > docker_save_console.log > 2>&1 &
docker save 192.168.15.143:5000/dmp:1.2.5 > /opt/docker/dmp_img20230326.tar


# ��һ�´洢λ��
docker info
#  Docker Root Dir: /data/docker/lib/docker
# ��һ�´洢λ��
nano /etc/docker/daemon.json
# ���¼���
systemctl daemon-reload
systemctl restart docker


Docker Root Dir: /var/lib/docker



{
"data-root": "/opt/docker-root-dir"
}