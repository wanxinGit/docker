https://hub.docker.com/r/marvambass/subversion/

1��svn�����ű�
docker run \
-d \
-p 80:80 -p 443:443 \
-v /opt/docker/storage/subversion/svn:/var/local/svn \
-v /opt/docker/storage/subversion/backup:/var/svn-backup \
-v /opt/docker/storage/subversion/conf:/etc/apache2/dav_svn/ \
-v /etc/localtime:/etc/localtime:ro \
-e TZ="Asia/Shanghai" \
--restart always \
--name subversion marvambass/subversion


����
1������ubuntu:14.04����
2��svn�������httpd������Ĭ�϶˿���80��443
3�����svnĿ¼ֻ��Ҫ��/opt/docker/storage/subversion/svnĿ¼�´����ļ��У�ϵͳ��ÿ��10����ɨ��Ŀ¼��Ȼ���䴴��ΪsvnĿ¼��
4��ÿ������ͨ��svndump�Զ�ִ�б��ݲ���
5���޸�Ȩ��������Ҫ�޸�/opt/docker/storage/subversion/conf/dav_svn.authz�ļ�
6�������˻�������Ҫ�õ�httpd�Դ���htdigest���ߣ����뽫��������ʽ�洢�����Ӱ�ȫ
htdigest -c /opt/docker/storage/subversion/conf/dav_svn.passwd Subversion testuser
���������˻��Ͳ�Ҫ��-c������)
7��svn���ʷ�ʽ
http://ip/svn/(svn��Ŀ¼��)
8������һ��Ŀ¼svnĿ¼�����Զ����������⣬����֣�����ʱ�����������ģ�ÿʮ����ִ��
�����棬ʵ��ֻ��Ҫͨ���±߷�ʽ��ʱ���
cp /etc/crontab /tmp/crontab
cp /tmp/crontab /etc/crontab
--���ɻ��Ǹ�������ϵͳ�汾�йأ�����Щ�������ƺ�������֣�