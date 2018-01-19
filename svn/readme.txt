1、svn启动脚本
docker run \
-d \
-p 8080:80 -p 8443:443 \
-v /opt/docker/storage/subversion/svn:/var/local/svn \
-v /opt/docker/storage/subversion/backup:/var/svn-backup \
-v /opt/docker/storage/subversion/conf:/etc/apache2/dav_svn/ \
--name subversion marvambass/subversion

包含
1）基于ubuntu:14.04制作
2）svn服务基于httpd，所以默认端口是80和443
3）添加svn目录只需要在/opt/docker/storage/subversion/svn目录下创建文件夹（系统会每个10分钟扫描目录，然后将其创建为svn目录）
4）每天零点会通过svndump自动执行备份操作
5）修改权限配置需要修改/opt/docker/storage/subversion/conf/dav_svn.authz文件
6）配置账户密码需要用到httpd自带的htdigest工具，密码将以密文形式存储，更加安全
htdigest -c /opt/docker/storage/subversion/conf/dav_svn.passwd Subversion testuser