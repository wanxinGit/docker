docker run -d \
-v /opt/docker/storage/ftp/data:/home/vsftpd \
-v /opt/docker/storage/ftp/logs:/var/log/vsftpd/ \
-p 2340:20 \
-p 2341:21 \
-p 21100-21110:21100-21110 \
-e FTP_USER=hit \
-e FTP_PASS=hit123456 \
-e PASV_MIN_PORT=21100 \
-e PASV_MAX_PORT=21110 \
-e PASV_ADDRESS=172.19.20.5 \
-e LOG_STDOUT=1 \
--name vsftpd --restart=always fauria/vsftpd


注：
1、PASV_ADDRESS中的ip地址必须固定，为宿主机的IP地址(因为有被动模式的存在)
2、ftp的2个端口号必须相差1
3、添加新的用户步骤：
	docker exec -i -t vsftpd bash
	mkdir /home/vsftpd/myuser
	chown -R ftp:ftp /home/vsftpd(请别忘了这一句的执行否者写文件的时候可能会存在没有权限问题)
	echo -e "myuser\nmypass" >> /etc/vsftpd/virtual_users.txt
	/usr/bin/db_load -T -t hash -f /etc/vsftpd/virtual_users.txt /etc/vsftpd/virtual_users.db
	exit
	docker restart vsftpd