docker run -idt  \
--name samba_docker  \
-p 139:139 -p 445:445  \
-v /opt/docker/storage/samba:/home/shares/mount \
-d dperson/samba \
-w "WORKGROUP" -u "sambaUser;123456" -s "mount;/home/shares/mount;yes;no;no;sambaUser;sambaUser;sambaUser"

注：
1、sambaUser用户不需要在系统中创建。
2、如果更改了用户名重新启动容器，win资源管理器中访问可能出现“不允许一个用户使用一个以上用户名与服务器或共享资源的多重连接”的错误，需要cmd中执行如下：
net use * /del /y