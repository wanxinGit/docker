sudo docker run -it --name samba -p 139:139 -p 445:445 \
            -v /opt/docker/storage/samba:/mount \
            -d dperson/samba


docker run -idt -p 139:139 -p 445:445 \
	-v /opt/docker/storage/samba:/mount \
	-u "testsamba;654321" \
	dperson/samba


##目前的版本有问题，后续有时间再研究下~