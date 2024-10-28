https://hub.docker.com/r/uozi/nginx-ui/tags

https://github.com/0xJacky/nginx-ui

docker pull uozi/nginx-ui

docker run -idt \
--name=nginx-ui \
--restart=always \
-e TZ=Asia/Shanghai \
-p 8087:80 \
-p 8444:443 \
-v /opt/docker/storage/nginx-ui/ngetc:/etc/nginx \
-v /opt/docker/storage/nginx-ui/uietc:/etc/nginx-ui \
-v /opt/docker/storage/nginx-ui/www:/var/www \
uozi/nginx-ui