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