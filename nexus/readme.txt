1��nexus3�����ű�
docker run -d \
-p 8082:8081 \
-p 8084:8084 \
-p 8085:8085 \
-p 8086:8086 \
--name nexus \
-v /opt/docker/storage/nexus-data:/nexus-data \
--restart always \
sonatype/nexus3

1��Ĭ���˺�����admin/admin123
2����Ҫ����/opt/docker/storage/nexus-dataĿ¼�������û�Ȩ��
3��8084��8085��8086�˿�Ϊdocker����ֿ�����(8084Ϊhost�ֿ⣬8085Ϊproxy�ֿ⣬8086Ϊ������ǰ������group�ֿ�)