# ����һ�����׵�DNS������
docker run \
	--name dns \
	-d \
	-p 53:53/tcp \
	-p 53:53/udp \
	-p 5380:8080 \
	--log-opt "max-size=100m" \
	-e "HTTP_USER=foo" \
	-e "HTTP_PASS=bar" \
	--restart always \
	jpillora/dnsmasq

# ����DNS������
docker run -idt --link dns:dns centos:7.4.1708

#�ڲ���������ִ�������Բ��ԣ�
host myhost.company dns
�����Ҫ�޸�DNS�����������webҳ��������
http://192.168.26.110:5380/
Ҳ����ֱ��ӳ�䱾�ص������ļ���������
-v /opt/dnsmasq.conf:/etc/dnsmasq.conf

�������õ���dnsmasq���������ϸ���ܺͲ������������
http://www.thekelleys.org.uk/dnsmasq/docs/dnsmasq-man.html