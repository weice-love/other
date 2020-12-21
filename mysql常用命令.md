show PROCESSLIST;
show variables like '%max_connections%';
show status like 'Threads%';
# 刷新配置
flush privileges;
# 授予权限
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'mypassword' WITH GRANT OPTION;
# 查看数据库表存储引擎 
show variables like '%storage_engine%';
# 查看当前表的选择的存储引擎
show create table `fuel_station`;

# 创建备份表
CREATE TABLE IF NOT EXISTS XXX_COPY LIKE XXX;
INSERT INTO XXX_COPY SELECT * FROM	XXX WHERE condition = true;