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

################################ʵ������##################################################

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

���������⣺
1����������ʲ���
�����鿴tomcat������־���֣�������deploy�׶�
�ٶȵ�֪����Ϊcentos7����ȫ��ԭ����Ҫ�޸�jre�µ��ļ�jre/lib/security/java.security
��securerandom.source=file:/dev/urandom ��Ϊ securerandom.source=file:/dev/./urandom����

2��tomcat����������������
��������Dockerfile�������滻catalina.sh����������û��Ҫ������ֱ��tomcat��ѹ��������ļ�
JAVA_OPTS='-Xms1024m -Xmx1024m -XX:PermSize=256M -XX:MaxNewSize=512m -XX:MaxPermSize=512m'