## MySQL部署

```
# 创建网络
docker network create -d bridge mysql-net-srever
# 创建mysql服务（主）
docker run -p 33006:3306 --network mysql-net-server --name mysql33006 -v /home/mysql/mysql_33006/conf:/etc/mysql/ -v /home/mysql/mysql_33006/data:/var/lib/mysql -v /home/mysql/mysql_33006/binlog:/home/mysql/binlog -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
# 创建mysql服务（从）
docker run -p 33007:3306 --network mysql-net-server --name mysql33007 -v /home/mysql/mysql_33007/conf:/etc/mysql/ -v /home/mysql/mysql_33007/data:/var/lib/mysql -v /home/mysql/mysql_33007/binlog:/home/mysql/binlog -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7

# 主节点配置文件
[mysqld]
server-id=1
log-bin=mysql-bin   
# binlog_format=row

# 从节点配置文件
[mysqld]
## 设置server_id,注意要唯一
server-id=2
## 开启二进制日志功能，以备Slave作为其它Slave的Master时使用
log-bin=mysql-slave-bin
## relay_log配置中继日志
relay_log=edu-mysql-relay-bin

# 文件配置好后，重启mysql服务
docker restart <容器id>

# 进入主节点的mysql命令行，进行主节点账号权限配置
1. 创建从库用户
CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
2. 授权（REPLICATION CLIENT（不知道什么用））
GRANT REPLICATION SLAVE,  ON *.* TO 'slave'@'%';

# 在master节点，查看状态，并记录下File,Position的值
show master status;

# 进入从节点mysql命令行, 修改master相关配置
change master to master_host='<docker服务主节点的mysql服务名>', master_user='<在主节点创建同步用户>', master_password='<密码>', master_port=3306, master_log_file='<主节点file的值>', master_log_pos= <主节点Position的值>, master_connect_retry=30;

# 启动slave服务
start slave;
# 查看从节点状态
show slave status;

```

