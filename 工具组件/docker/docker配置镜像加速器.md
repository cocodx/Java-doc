1,登录阿里云 
2，点击控制台  
3，搜索容器镜像服务  
4，最下面的镜像加速器。  

```java
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://1ni7cdm1.mirror.aliyuncs.com"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```