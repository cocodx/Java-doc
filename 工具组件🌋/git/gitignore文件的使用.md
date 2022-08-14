使用方法在git管理下的文件夹下，添加文件名为：
```dotnetcli
.gitignore
```

里面就是不被git管理的文件。  
使用规则：


对于已经提交的文件清理
```java
git rm -r --cached .idea
```