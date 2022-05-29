什么是容器呢？  

好比镜像就是.java文件，编译之后的class字节码文件就是容器。  

* 查看容器
```java
docker ps # 查看正运行的容器
docker ps -a #查看历史容器记录
```

* 创建容器
```java
docker run -it --name=c1 redis /bin/bash  
-i:让容器的标准输入保持打开  
-t:让Docker分配一个伪终端并绑定到容器的标准输入上  
--name:起一个名字
-d:以守护后台方式与西宁，使用docker exec进入容器。退出后容器不会关闭
```

* 退出容器
```java
exit
```

* 进入容器
```java
docker exec -it c2 /bin/bash
```
* 启动容器
```java
docker run -id --name=c2 redis #这是第一次运行
docker start c2 #启动
```
* 停止容器
```java
docker stop c2
```

* 删除容器
```java
docker rm containerId[容器id|name]
docker rm `docker ps -aq`  #删除所有的容器
```

* 查看容器信息
```java
docker inspect name[容器名称]
```