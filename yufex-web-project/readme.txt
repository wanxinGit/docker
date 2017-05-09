�˾������ļ�����jenkins�����������ڴ����ύ���Զ�����war����������һ���µ���������������

���岽��
1������Dockerfile����һ��tomcat����
--����jdk1.7
--tomcat���л���
--Ĭ��webapps��һ��ֻ��hello world��war��

2����jenkins������һ��maven��Ŀ����Ҫ�Զ���֪�����ı仯�����Զ���������

3�������ɹ�������Publish Over SSH��jenkins��������Զ��sshָ������docker�����������������������ⲿ������

4��ִ������ָ���docker����������¼�������
	1��ɾ�����ڵ�����
	2��������������ͨ�����ؾ����ʽ�����µ�war����

################################jenkinsʵ������ yufex-wtms##################################################
Source files��yufex-wtms/target/yufex-wtms.war
Remove prefix��yufex-wtms/target/
Remote /opt/docker/storage/tomcat/yufex-wtms/temp
Exec command��

PROJECT_NAME=yufex-wtms
PROJECT_PORT=8090

#remove exits container
if docker ps -a | grep -i ${PROJECT_NAME}; then
	docker rm -f ${PROJECT_NAME}
fi

#������һ��war�����ƶ��°���trunkĿ¼
cd /opt/docker/storage/tomcat/yufex-wtms
rm -rf bak
mv trunk bak
mkdir trunk
mv temp/${PROJECT_NAME}.war trunk/ROOT.war
rm -rf temp

#run new container
docker run -d \
-p ${PROJECT_PORT}:8080 \
--link mysql:local_mysql \
-v /opt/docker/base_env:/opt/base_env \
-v /etc/localtime:/etc/localtime:ro \
-v /opt/docker/storage/tomcat/${PROJECT_NAME}/trunk:/opt/server/apache-tomcat-7.0.77/webapps \
-e TZ="Asia/Shanghai" \
--name ${PROJECT_NAME} \
--restart always \
116.62.71.194:5000/tomcat:v1

###########################################################################################

################################jenkinsʵ������ yufex-wtweb##################################################
Source files��yufex-wtweb/target/yufex-wtweb.war
Remove prefix��yufex-wtweb/target/
Remote directory��/opt/docker/storage/tomcat/yufex-wtweb/temp
Exec command��

PROJECT_NAME=yufex-wtweb
PROJECT_PORT=8080

#remove exits container
if docker ps -a | grep -i ${PROJECT_NAME}; then
	docker rm -f ${PROJECT_NAME}
fi

#������һ��war�����ƶ��°���trunkĿ¼
cd /opt/docker/storage/tomcat/${PROJECT_NAME}
rm -rf bak
mv trunk bak
mkdir trunk
mv temp/${PROJECT_NAME}.war trunk/ROOT.war
rm -rf temp

#run new container
docker run -d \
-p ${PROJECT_PORT}:8080 \
--link mysql:local_mysql \
-v /opt/docker/base_env:/opt/base_env \
-v /etc/localtime:/etc/localtime:ro \
-v /opt/docker/storage/tomcat/${PROJECT_NAME}/trunk:/opt/server/apache-tomcat-7.0.77/webapps \
-e TZ="Asia/Shanghai" \
--name ${PROJECT_NAME} \
--restart always \
116.62.71.194:5000/tomcat:v1

###########################################################################################

���������⣺
1����������ʲ���
�����鿴tomcat������־���֣�������deploy�׶�
�ٶȵ�֪����Ϊcentos7����ȫ��ԭ����Ҫ�޸�jre�µ��ļ�jre/lib/security/java.security
��securerandom.source=file:/dev/urandom ��Ϊ securerandom.source=file:/dev/./urandom����

2��tomcat����������������
��������Dockerfile�������滻catalina.sh����������û��Ҫ������ֱ��tomcat��ѹ��������ļ�
JAVA_OPTS='-Xms1024m -Xmx1024m -XX:PermSize=256M -XX:MaxNewSize=512m -XX:MaxPermSize=512m'

3���Զ������汾��仯��Ȼ���������
ͨ��svn hooks���ƽ����git�ϴ����Ϊ���㣩

4��ʱ������
ͨ������localtime������TZ�������

5�������ظ���������
���ھ��񹹽������ʱ��apt-get��װ���ߣ������Բ����ȹ����þ���Ȼ��ÿ��ֻ��ɾ���������������������ķ�ʽ��

6����־�����������