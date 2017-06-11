docker pull zwh8800/xware

docker run \
--security-opt=seccomp:unconfined \
--name xware \
-v /opt/docker/storage/xware/:/data \
-d \
zwh8800/xware