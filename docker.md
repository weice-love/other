#### docker安装

ubuntu: curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun



### 切换镜像源

1. vim /etc/docker/daemon.json      ##没有该文件则自己创建
2. { "registry-mirrors": ["https://hub-mirror.c.163.com", "[https://docker.mirrors.ustc.edu.cn](https://docker.mirrors.ustc.edu.cn/)", "https://6kx4zyno.mirror.aliyuncs.com"] }
3. systemctl daemon-reload
4. systemctl restart docker.service   ##重启服务

### 安装中文字体

```
      \nRUN echo "deb http://httpredir.debian.org/debian jessie main contrib" > /etc/apt/sources.list
      \nRUN echo "deb http://security.debian.org/ jessie/updates main contrib" >> /etc/apt/sources.list
      \nRUN echo "ttf-mscorefonts-installer msttcorefonts/accepted-mscorefonts-eula select true" | debconf-set-selections
      \nRUN apt-get update
      \nRUN apt-get install -y ttf-mscorefonts-installer
      \nRUN apt-get clean
      \nRUN apt-get autoremove -y
      \nRUN rm -rf /var/lib/apt/lists/*
```

#### 创建网络

```
#-d 指定网络类型（bridge，overlay）
docker network create -d {网络类型} {网络名称}
demo:
	docker network create -d bridge s
```

