#### 安装python相关

1. 下载[vscode](https://code.visualstudio.com/)，页面比较好看。

2. 点击安装，一路下一步，直到安装成功。

3. 下载[anaconda](https://www.anaconda.com/), 一个开源的[Python](https://baike.baidu.com/item/Python)发行版本，其包含了[conda](https://baike.baidu.com/item/conda/4500060)、Python等180多个科学包及其依赖项。

4. 点击安装，在anaconda添加进系统路径的框打勾（2020.11的版本，默认没有打勾）

5. anaconda配置国内源

   ```
   conda config --add channels 'https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/'      //TUNA的help中镜像地址加有引号，需要去掉# 
   conda config --add channels 'https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/'
   conda config --set show_channel_urls yes      // 安装包时显示channel的url
   
   ### 查看管道
   conda config --show channels
   ```

6. 常用命令

   ```
   conda install <包名> 安装指定包
   conda remove <包名> 移除指定包
   conda update <包名> 更新指定包
   conda create -n 环境的名字 python=版本号
   conda install --use-local xxx
   
   #安装本地whl
   1. 打开Anaconda Command Prompt
   2. 切换环境 conda activate env
   3. pip3 install 本地whl
   ```

   

