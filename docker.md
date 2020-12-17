### 切换镜像源

1. vim /etc/docker/daemon.json      ##没有该文件则自己创建
2. { "registry-mirrors": ["https://hub-mirror.c.163.com", "[https://docker.mirrors.ustc.edu.cn](https://docker.mirrors.ustc.edu.cn/)", "https://6kx4zyno.mirror.aliyuncs.com"] }
3. systemctl daemon-reload
4. systemctl restart docker.service   ##重启服务

