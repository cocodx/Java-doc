通过Docker容器中部署Mysql，并通过外部mysql客户端操作MySQL Server

当容器中网络服务需要被外部机器访问时，可以将容器中提供服务的端口映射到宿主机的端口上。外部机器访问宿主机的该端口，从而间接容器的服务。

端口映射。

```java
docker search mysql
docker pull mysql:5.6

mkdir ~/mysql
cd ~/mysql

docker run -id \
-p 3307:3306 \
--name=c_mysql \
-v /root/mysql/conf:/etc/mysql/conf.d \
-v /root/mysql/logs:/logs \
-v /root/mysql/data:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
mysql:5.6
```

docker exec -it c_mysql /bin/bash

mysql -uroot -p123456