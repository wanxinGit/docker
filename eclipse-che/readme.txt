docker run -it \
--rm \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /opt/docker/storage/che:/data \
eclipse/che:rc start


注：
启动还有报错，后面有时间研究下吧。。。