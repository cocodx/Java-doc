##### 1.1点击安装按钮
![image](../../images/1646845520(1).jpg)
##### 1.2选择高级
![image](../../images/1646845639(1).jpg)
##### 1.3默认下一步
![image](../../images/Snipaste_2022-03-10_01-08-06.png)
##### 1.4选择稍后安装
![image](../../images/Snipaste_2022-03-10_01-09-25.png)
##### 1.5选择对应的
![image](../../images/Snipaste_2022-03-10_01-10-24.png)
##### 1.6设置名称和安装位置
![image](../../images/Snipaste_2022-03-10_01-12-00.png)
##### 1.7默认就好
![image](../../images/Snipaste_2022-03-10_01-12-54.png)
##### 1.8由于我电脑是32GB内存，设置2GB就可以了
![image](../../images/Snipaste_2022-03-10_01-13-45.png)
##### 1.9网络选择NAT
![image](../../images/Snipaste_2022-03-10_01-16-00.png)
##### 2.0下两步默认
![image](../../images/Snipaste_2022-03-10_01-16-16.png)
![image](../../images/Snipaste_2022-03-10_01-16-31.png)

##### 2.1点击创建新虚拟磁盘
![image](../../images/Snipaste_2022-03-10_01-17-39.png)
##### 2.2磁盘给20GB，将虚拟磁盘拆分成多个文件
![image](../../images/Snipaste_2022-03-10_01-19-01.png)
##### 2.3点击下一步
![image](../../images/Snipaste_2022-03-10_01-19-21.png)

##### 2.4已经添加进去了
![image](../../images/Snipaste_2022-03-10_01-20-40.png)
##### 2.5编辑虚拟机位置
![image](../../images/Snipaste_2022-03-10_01-22-00.png)

##### 2.6点击确定，然后开启虚拟机，然后install
![image](../../images/Snipaste_2022-03-10_01-22-00.png)

##### 2.7选择中文
![image](../../images/Snipaste_2022-03-10_01-24-05.png)

##### 3.0重启之后，ip addr
![image](../../images/Snipaste_2022-03-10_01-34-05.png)
发现ens33，没有inet属性，那么就没法通过IP连接虚拟机。

接着来查看ens33网卡的配置： vi /etc/sysconfig/network-scripts/ifcfg-ens33   注意vi后面加空格

vi是Linux内置的文本编辑器命令 打开文件的意思
![image](../../images/Snipaste_2022-03-10_01-36-08.png)
改为yes

然后重启网络服务： sudo service network restart 

然后再 ip addr查看
![image](../../images/Snipaste_2022-03-10_01-38-02.png)

要把网络再改成桥接模式，不勾选复制，才行，再重启，如此美妙的画面
![image](../../images/Snipaste_2022-03-10_01-47-02.png)
