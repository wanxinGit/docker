https://hub.docker.com/r/dockurr/windows

# on local vmware
docker run -it --rm \
--name windows \
-e VERSION="ltsc10" \
-e RAM_SIZE="4G" \
-e CPU_CORES="2" \
-e DISK_SIZE="64G" \
-e MANUAL="Y" \
-p 8006:8006 \
-p 3389:3389/tcp \
-p 3389:3389/udp \
-v /opt/docker/windows/storage:/storage \
-v /opt/docker/windows/shared:/shared \
--privileged=true \
--device=/dev/kvm \
--cap-add NET_ADMIN \
--stop-timeout 120 \
dockurr/windows:3.11


# on 172.19.20.5
docker run -it --rm \
--name windows \
-e VERSION="win11" \
-e RAM_SIZE="4G" \
-e CPU_CORES="2" \
-e DISK_SIZE="64G" \
-e MANUAL="Y" \
-p 8006:8006 \
-p 3389:3389/tcp \
-p 3389:3389/udp \
-v /opt/docker/storage/windows/storage:/storage \
--privileged=true \
--device=/dev/kvm \
--cap-add NET_ADMIN \
--stop-timeout 120 \
dockurr/windows:3.11