@echo off
start cmd /k "cd/d D:\\Redis-x64-3.2.100 &&redis-server.exe &&taskkill /f /t /im cmd.exe"
