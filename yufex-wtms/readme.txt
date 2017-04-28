此镜像构造文件用于jenkins持续构建，在代码提交后自动生成war包，并拉起一个新的容器供测试所用

大体步骤
1、需要在jenkins上配置一个maven项目，需要自动感知代码库的变化，并自动触发构建（Git的钩子机制可以提交后立即触发，SVN似乎不行，只有定时主动update检查更新）

2、构建成功后，利用Publish Over SSH（jenkins插件）完成远程ssh指令连接docker机器（可能是宿主机或者外部机器）

3、执行如下指令，在docker机上完成如下几个步骤
	1）war包拷贝，更名ROOT.war
	2）原本镜像的删除
	3）新镜像的构建（根据需要可能需要上传到仓库）
	4）删除存在的容器
	5）根据刚刚构建的镜像启一个新的容器

################################实例代码##################################################

#war name change
cd /opt/docker/build/yufex-wtms/war
rm -f ROOT.war
mv yufex-wtms.war ROOT.war

#remove old images
if docker images | grep -i yufex/yufex-wtms; then
	docker rmi -f yufex/yufex-wtms
fi

#build image by Dockerfile
cd /opt/docker/build/yufex-wtms
docker build -t yufex/yufex-wtms .

#remove exits container
if docker ps -a | grep -i yufex-wtms; then
	docker rm -f yufex-wtms
fi

#run new container
docker run -d -p 8090:8080 --link mysql:local_mysql -v /opt/docker/base_env:/opt/base_env --name yufex-wtms --restart always yufex/yufex-wtms

###########################################################################################

遇到的问题：
1、启动后访问不了
经过查看tomcat启动日志发现，卡在了deploy阶段
百度得知，因为centos7本身安全性原因，需要修改jre下的文件jre/lib/security/java.security
将securerandom.source=file:/dev/urandom 改为 securerandom.source=file:/dev/./urandom即可

2、tomcat启动参数配置问题
本来想在Dockerfile里配置替换catalina.sh，后来觉得没必要，不如直接tomcat的压缩包里的文件
JAVA_OPTS='-Xms1024m -Xmx1024m -XX:PermSize=256M -XX:MaxNewSize=512m -XX:MaxPermSize=512m'