```java
docker search nginx
docker pull nginx
```

查看镜像
```java
docker images
```

创建目录存储tomcat产生数据
```java
mkdir ~/nginx
cd ~/nginx
```

启动
```java
docker run -id --name=c_nginx \
-p 80:80 \
-v /root/nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-v /root/nginx/logs:/var/log/nginx \
-v /root/nginx/html:/usr/share/nginx/html \
nginx
```

出现的原因是 $PWD/conf/nginx.conf 是一个目录，而不是文件

解决方法就是删除nginx.conf 目录，然后新建一个nginx.conf 文件就可以了。

访问http://192.168.125.61/