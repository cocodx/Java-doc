1、tomcat启动失败

At least one of these environment variable is needed to run this program

这个是tomcat，启动的时候，没有读到jdk的配置路径。

查看linux环境配置
```java
cat /etc/profile

export JAVA_HOME=/home/richmail/jdk
export JRE_HOME=/home/richmail/jdk/jre
```
在tomcat的bin目录下的catalina.sh里面修改
```xml

```
![image](../images/1647251092(1).jpg)

2、