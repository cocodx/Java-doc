查看firewalld状态
> systemctl status firewalld

关闭firewalld
> systemctl stop firewalld

开机禁用firewalld
> systemctl disable firewalld

**ssh默认端口是22**
**ftp默认端口是21**

解压gz文件
> tar zxvf jdk-8u211-linux-x64.tar.gz

建立软连接，它的功能是为某一个文件在另外一个位置建立一个不同的链接
> ln -s 源文件 目标文件

启动zookeeper，进行bin目录
> ./zkServer.sh start