```java
# github下载 arthas
wget https://alibaba.github.io/arthas/arthas-boot.jar

# 启动
java -jar arthas-boot.jar
```
在启动之前，最好使用jps可以显示一下当前可用的线程，再启动arthas
```dotnetcli
# 开启控制台，几秒钟刷新一下
dashboard
# 显示线程堆栈
thread

# 显示某某线程具体情况
thread id

# 找死锁的代码
thread -b

# jad反编译线上正在运行的代码
jad com.liugang.jvm.Jvm(包名.类名)

# 显示命令大全列表
help

# ognl命令可以查看线上系统变量的值，甚至可以修改变量的值

```

GC日志详解
%t 代表时间
