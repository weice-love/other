## GIT常用命令表

##### 分支定义

- master: 默认开发分支
- origin: 默认远程版本库
- Head: 默认开发分支
- Head^: Head的父提交

##### 创建版本库

- git clone <url>  			# 克隆远程版本库
- git init                              # 初始化本地版本库

##### 修改和提交

- git status                          # 查看状态
- git diff                               # 查看变更内容
- git add .                            # 添加修改的所有文件
- git add <file>                # 添加指定的文件
- git mv <old> <new>        # 修改文件名称
- git rm  <file>                  # 删除文件
- git rm --cached <file>    # 停止跟踪文件但不删除
- git commit -m "msg"          # 提交更新的文件
- git commit --amend            # 修改最后一次提交