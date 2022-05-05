#### 1.1 windows系统下nginx的使用命令

打开nginx的目录，cmd

* start nginx |启动nginx
* nginx -t |检查nginx.conf是否符合规范，能够正常启动
* nginx -s reload |重新启动nginx 

#### 1.2 nginx配置请求负载
```java
upstream stockService{
        server 127.0.0.1:8080;
        server 127.0.0.1:8081;
    }

server{
    #监听端口
    listen       9001;
    #自己的服务器地址; # 域名或ip
    server_name  localhost;
    ......
    location ~ /deduct_stock/ {
        proxy_pass http://stockService;
    }
}
```
这样浏览器请求http://localhost:9001/deduct_stock/
就转发到了实际地址http://localhost:8080/deduct_stock/和http://localhost:8081/deduct_stock/