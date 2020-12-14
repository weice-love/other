#### easymock安装过程

1. 下载[easymock项目](# https://gitee.com/mirrors/Easy-Mock.git)
2. 下载安装对应的docker、docker-compose(sudo curl -L https://github.com/docker/compose/releases/download/1.27.4/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose)
3. 将下载完成的docker-compose，改为可执行文件（sudo chmod +x /usr/local/bin/docker-compose），查看是否安装成功(docker-compose --version)
4. 在下载的easymock项目的文件夹中创建[docker-compose.yml](./docker-compose.yml.yml)文件
5.  sudo docker-compose up -d 安装镜像服务



ps: [参考地址](https://github.com/easy-mock/easy-mock-docker)

