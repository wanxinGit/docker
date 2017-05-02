�˾������ļ�����jenkins�����������ڴ����ύ���Զ�����war����������һ���µ���������������

���岽��
1����Ҫ��jenkins������һ��maven��Ŀ����Ҫ�Զ���֪�����ı仯�����Զ�����������Git�Ĺ��ӻ��ƿ����ύ������������SVN�ƺ����У�ֻ�ж�ʱ����update�����£�

2�������ɹ�������Publish Over SSH��jenkins��������Զ��sshָ������docker�����������������������ⲿ������

3��ִ������ָ���docker����������¼�������
	1��war������������ROOT.war
	2��ԭ�������ɾ��
	3���¾���Ĺ�����������Ҫ������Ҫ�ϴ����ֿ⣩
	4��ɾ�����ڵ�����
	5�����ݸոչ����ľ�����һ���µ�����

################################jenkinsʵ������ yufex-wtms##################################################
Source files��yufex-wtms/target/yufex-wtms.war
Remove prefix��yufex-wtms/target/
Remote directory��/opt/docker/build/yufex-web-project/war
Exec command��

PROJECT_NAME=yufex-wtms
PROJECT_PORT=8090
#war name change
cd /opt/docker/build/yufex-web-project/war
rm -f ROOT.war
mv ${PROJECT_NAME}.war ROOT.war

#remove old images
if docker images | grep -i yufex/yufex-web-project; then
	docker rmi -f yufex/yufex-web-project
fi

#build image by Dockerfile
cd /opt/docker/build/yufex-web-project
docker build -t yufex/yufex-web-project .

#remove exits container
if docker ps -a | grep -i ${PROJECT_NAME}; then
	docker rm -f ${PROJECT_NAME}
fi

#run new container
docker run -d -p ${PROJECT_PORT}:8080 --link mysql:local_mysql -v /opt/docker/base_env:/opt/base_env -v /etc/localtime:/etc/localtime:ro -e TZ="Asia/Shanghai" --name ${PROJECT_NAME} --restart always yufex/yufex-web-project

###########################################################################################

################################jenkinsʵ������ yufex-wtweb##################################################
Source files��yufex-wtweb/target/yufex-wtweb.war
Remove prefix��yufex-wtweb/target/
Remote directory��/opt/docker/build/yufex-web-project/war
Exec command��

PROJECT_NAME=yufex-wtweb
PROJECT_PORT=8080
#war name change
cd /opt/docker/build/yufex-web-project/war
rm -f ROOT.war
mv ${PROJECT_NAME}.war ROOT.war

#remove old images
if docker images | grep -i yufex/yufex-web-project; then
	docker rmi -f yufex/yufex-web-project
fi

#build image by Dockerfile
cd /opt/docker/build/yufex-web-project
docker build -t yufex/yufex-web-project .

#remove exits container
if docker ps -a | grep -i ${PROJECT_NAME}; then
	docker rm -f ${PROJECT_NAME}
fi

#run new container
docker run -d -p ${PROJECT_PORT}:8080 --link mysql:local_mysql --link yufex-wtms:yufex-wtms -v /opt/docker/base_env:/opt/base_env -v /etc/localtime:/etc/localtime:ro -e TZ="Asia/Shanghai" --name ${PROJECT_NAME} --restart always yufex/yufex-web-project

###########################################################################################

���������⣺
1����������ʲ���
�����鿴tomcat������־���֣�������deploy�׶�
�ٶȵ�֪����Ϊcentos7����ȫ��ԭ����Ҫ�޸�jre�µ��ļ�jre/lib/security/java.security
��securerandom.source=file:/dev/urandom ��Ϊ securerandom.source=file:/dev/./urandom����

2��tomcat����������������
��������Dockerfile�������滻catalina.sh����������û��Ҫ������ֱ��tomcat��ѹ��������ļ�
JAVA_OPTS='-Xms1024m -Xmx1024m -XX:PermSize=256M -XX:MaxNewSize=512m -XX:MaxPermSize=512m'

3���Զ������汾��仯��Ȼ������������������

4��ʱ�����⣨�������

5�������ظ������������������