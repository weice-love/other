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

##### windows 启动java项目命令

```
@echo off

for /f "tokens=1" %%i in ('jps -m ^| find "xxx.jar"') do ( taskkill /F /PID %%i )

chcp 65001
java -jar .\xxx.jar  --spring.profiles.active=dev  --server.port=15001 
pause
```

#### 服务启动服务

```
@echo off
start cmd /k "cd/d D:\\Redis-x64-3.2.100 &&redis-server.exe &&taskkill /f /t /im cmd.exe"
```

