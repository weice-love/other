1. 到官网（http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html）下载jdk(.tar.gz的文件)
2. 创建jdk安装目录   mkdir java
3. 将下载好的压缩包放入刚创建好的文件夹中，并解压 tar -zxvf xxx
4. 配置环境变量  vim /etc/profile
5. 文件末尾添加以下内容
   - export JAVA_HOME={目录地址}/{jdk目录}
   - export JRE_HOME={目录地址}/{jdk目录}/jre
   - export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
   - export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
6. 使用当前配置 source /etc/profile
7. 验证 java -version