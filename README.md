# docker

the docker project can be run with

docker run -d \
-p 3690:3690 \
-v /opt/docker/storage/svn/repo:/opt/svn/repo \
--restart always \
--name svn
yufex/svn:1.0.0
