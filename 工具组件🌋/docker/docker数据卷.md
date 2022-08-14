* 数据卷是宿主机中的一个目录或文件  
* 当容器目录和数据卷目录绑定后，对方的修改会立即同步
* 一个数据卷可以被多个容器同时挂载
* 一个容器也可以挂载多个数据卷

#### 设置数据卷
```java
docker run -v 宿主机目录（文件）：容器内目录（文件）.....
```

1. 目录必须是绝对路径
1. 如果目录不存在，自动创建
1. 可以挂载多个目录

```java
docker run  -it --name=c4 -v /root/data:/root/data_container redis /bin/bash
```
然后在/root/data文件夹下新建文件，在data_container里面直接同步过去。有点像目录映射


```java
docker run -it --name=c1 -v ~/data:/root/data_container redis /bin/bash
```

重新创建一个容器，发现文件还在，类似于持久化的操作  

挂载两个目录：
```java
docker run -it --name=c2 -v ~/data2:/root/data2 -v ~/data3:/root/data3 redis
```

#### 两个容器挂载同一个数据卷

```java
docker run -it --name=c3 -v ~/data:/root/data redis
docker run -it --name=c4 -v ~/data:/root/data redis /bin/bash
docker run -it --name=c5 -v ~/data:/root/data redis /bin/bash
```

就可以看到在c4，c5里边的root/data里面的数据文件是一样的  

#### 数据卷容器
多容器进行数据交换
1. 多个容器挂载同一个数据卷
1. 数据卷容器

配置数据卷容器  
1.创建启动c3数据卷容器，使用-v参数 设置数据卷
```java
docker run -it --name=c3 -v /volume redis /bin/bash
```
2.创建启动c1 c2容器，使用--volumes-from参数设置 数据卷
```java
docker run -it --name=c1 --volumes-from c3 redis /bin/bash
docker run -it --name=c2 --volumes-from c3 redis /bin/bash
```