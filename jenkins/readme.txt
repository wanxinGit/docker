1��������ڹٷ�jenkins:2.46.1����

2��������������������������/opt/docker/base_envĿ¼����jdk��tomcat��maven�ȣ�

3��maven��setting�ļ��õ�config/setting.xml��������ģ����滻��

4���Ƽ���������
docker run \
-d \
-p 8081:8080 \
-p 50000:50000 \
-v /opt/docker/base_env:/opt/base_env \
-v /opt/docker/storage/jenkins:/var/jenkins_home \
--restart always 
--name jenkins yufex/jenkins:v1