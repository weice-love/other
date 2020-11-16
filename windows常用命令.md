###### 查找被占用的端口
netstat -ano | findstr "1080"
##### 根据进程父ID杀死进程
taskkill /pid 5588 /f
##### 修改shell编码（临时）
chcp 65001
#### 远程连接dubbo
telnet host port
	ls
	invoke 包.方法(参数)

##### 生成ssh

ssh-keygen -t rsa -C "youremail@example.com" 