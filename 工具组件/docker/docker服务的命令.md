刚才说了docker是linux里边的一个软件，就跟在linux里面安装mysql，redis一样对吧。  

所以，它的操作命令是个什么样子呢？  

启动
```java
systemctl start docker
```

停止
```java
systemctl stop docker
```

查看状态
```java
systemctl status docker
```

重启
```java
systemctl restart docker
```

设置开启自启动
```java
systemctl enable docker
```