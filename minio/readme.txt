docker run -d \
   -p 9000:9000 \
   -p 9090:9090 \
   --name minio \
   -v /opt/storange/minio:/data \
   -e "MINIO_ROOT_USER=wanxin" \
   -e "MINIO_ROOT_PASSWORD=wx65251206" \
   minio/minio:RELEASE.2023-09-30T07-02-29Z server /data --console-address ":9090"