# 构建一个简易的DNS服务器
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

# 测试DNS服务器
docker run -idt --link dns:dns centos:7.4.1708

#在测试容器中执行语句可以测试：
host myhost.company dns
如果需要修改DNS配置项，可以在web页面中配置
http://192.168.26.110:5380/
也可以直接映射本地的配置文件到容器中
-v /opt/dnsmasq.conf:/etc/dnsmasq.conf

此容器用的是dnsmasq组件，其详细介绍和参数配置详见：
http://www.thekelleys.org.uk/dnsmasq/docs/dnsmasq-man.html