@echo off
start cmd /k "cd/d D:\seata-server-1.3.0\seata\bin &&seata-server.bat&&taskkill /f /t /im cmd.exe"
