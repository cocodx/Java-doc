```java
docker search tomcat
docker pull tomcat
```

查看镜像
```java
docker images
```

创建目录存储tomcat产生数据
```java
mkdir ~/tomcat
cd ~/tomcat
```

启动
```java
docker run -id --name=c_tomcat \
-p 8081:8080 \
-v /root/tomcat:/usr/local/tomcat/webapps \
tomcat
```

如果报错，执行一下
```java
重启docker服务后再启动容器
systemctl restart docker
docker start foo
```

访问http://192.168.125.61:8080