1、基础环境包放置在宿主机的/opt/docker/base_env目录（如jdk，tomcat，maven等）

2、*由于jenkins容器内默认用的jenkins账户权限，而不是root，所以有两个地方要注意，否则会报权限错误
1）5命令用到挂载/var/jenkins_home的宿主机目录，需要赋予其他用户读写的权限
chmod 777 /opt/docker/base_env

3、推荐启动命令
docker run \
-d \
-p 8081:8080 \
-p 50000:50000 \
-v /opt/docker/base_env:/opt/base_env \
-v /opt/docker/storage/jenkins:/var/jenkins_home \
--restart always \
--link svn:local_svn \
--name jenkins jenkins:2.46.1

4、拷贝自定义的setting.xml文件到/var/jenkins_home/.m2目录中