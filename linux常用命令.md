###### 查看操作系统得最大文件句柄数
cat /proc/sys/fs/file-max 
##### 上传文件
rz -y
##### 覆盖原有文件
unzip -o 1.zip
#### 将windows的换行符替换成linux换行
sed -i -e 's/\r$//' <文件名>

##### 统计某个字符串出现的次数

grep -o "xxx" file | wc -l

###### 请求接口

curl  -H '192.168.50.191/v1/email/sendEmail' -C 'Content-Type: application/json' -d '{"text": "aaa","to": ["xxx"]}'

### 查看监听端口

netstat -tnl

### 安装ssh

sudo apt-get install openssh-server openssh-client 

#### ubuntu 修改时区

1. date -R #查看时区
2. tzselect #选择时区
3. 数字选择: 4 -> 9-> 1 -> 1
4. sudo cp /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime #修改
5. 再次查看时区

#### ubuntu 修改host文件

	1. sudo vim /etc/hosts
	2. 添加需要解析的地址
	3. sudo /etc/init.d/networking restart #重启网络

#### 安装ping

​	sudo apt-get install iputils-ping

#### 安装网络工具

​	sudo apt install net-tools

#### 安装vim

​	apt-get install vim-gtk

#### 文件输入

​	echo "xxx" > file

​	echo "xxx" >> file # 追加文件

#### 安装nodejs

```
sudo apt install nodejs
sudo apt remove nodejs
sudo apt purge nodejs # 删除配置文件
sudo apt autoremove # 自动删除没有使用的包
```

#### 修改DNS,HOSTS

```
/etc/resoulv.conf
/etc/hosts
```

#### 查看服务状态

```
service --status-all
```

### 查看系统状态

```
uname -a # 内核信息
cat /proc/version # 当前操作系统版本信息
```

#### 开放指定端口

```
sudo apt-get install iptables
# 临时方案
iptables -I INPUT -p tcp --dport 80 -j ACCEPT
iptables-save
# 持久方案
sudo apt-get install iptables-persistent
sudo netfilter-persistent save
sudo netfilter-persistent reload
```


#### npm 临时换源
npm --registry https://registry.npm.taobao.org install express

#### npm 持久使用
npm config set registry https://registry.npm.taobao.org
// 配置后可通过下面方式来验证是否成功
npm config get registry

#### 查看端口情况

lsof -i:80
