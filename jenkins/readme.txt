1��������������������������/opt/docker/base_envĿ¼����jdk��tomcat��maven�ȣ�

2��*����jenkins������Ĭ���õ�jenkins�˻�Ȩ�ޣ�������root�������������ط�Ҫע�⣬����ᱨȨ�޴���
1��5�����õ�����/var/jenkins_home��������Ŀ¼����Ҫ���������û���д��Ȩ��
chmod 777 /opt/docker/base_env

3���Ƽ���������
docker run \
-d \
-p 8081:8080 \
-p 50000:50000 \
-v /opt/docker/base_env:/opt/base_env \
-v /opt/docker/storage/jenkins:/var/jenkins_home \
--restart always \
--link svn:local_svn \
--name jenkins jenkins:2.46.1

4�������Զ����setting.xml�ļ���/var/jenkins_home/.m2Ŀ¼��