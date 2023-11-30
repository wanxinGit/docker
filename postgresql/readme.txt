docker run -d \
--name postgres \
-e POSTGRES_PASSWORD=Cqpgx123321 \
-e TZ="Asia/Shanghai" \
-p 5432:5432 \
-v /opt/docker/storage/postgres/data:/var/lib/postgresql/data \
--restart always \
--privileged \
postgres
