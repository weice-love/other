@echo off
start cmd /k "cd/d D:\\nacos\\bin &&startup.cmd &&taskkill /f /t /im cmd.exe"
