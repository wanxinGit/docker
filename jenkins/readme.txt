1、镜像基于官方jenkins:2.46.1制作

2、基础环境包放置在宿主机的/opt/docker/base_env目录（如jdk，tomcat，maven等）

3、maven的setting文件用的config/setting.xml，如需更改，可替换。

4、推荐启动命令
docker run \
-d \
-p 8081:8080 \
-p 50000:50000 \
-v /opt/docker/base_env:/opt/base_env \
-v /opt/docker/storage/jenkins:/var/jenkins_home \
--restart always 
--name jenkins yufex/jenkins:v1