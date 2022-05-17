#### jmap命令是一个多功能的命令，可以生成java程序的dump文件，也可以查看堆内对象示例的统计信息，查看classLoader的信息以及finalizer队列。  
```java
jmap pid          //查看进程的内存映像信息
jmap -heap pid     //显示Java堆详细信息
jmap -histo:live pid //统计堆中对象的信息

jmap -dump:format=b,file=heapdump.hprof pid  //描述，生成堆转储快照dump文件。
JVM会将整个heap的信息dump写入一个文件，会暂停应用，线上系统谨慎使用。
```

#### jinfo查看虚拟机配置参数信息，调整虚拟机的配置参数。
```java
jinfo -sysprops pid 查看该进程的全部配置信息

jinfo -flags pid 查看曾经赋过值的参数值

jinfo -flag <具体参数> pid：查看具体参数的值
```

#### jstack，打印正在运行中的java进程的堆信息。  
```java
jstack pid  

使用top -Hp pid 查看进程下对应线程的CPU使用情况
然后把线程的id转成16进制，这时候就可以使用 jstack pid | grep -10 4b8f
```

#### jstat命令，查看堆内存各部分的使用量，以及加载类的数量。
```java
jstat gc pid //垃圾回收统计

S0C：第一个幸存区的大小
S1C：第二个幸存区的大小
S0U：第一个幸存区的使用大小
S1U：第二个幸存区的使用大小
EC：伊甸园区的大小
EU：伊甸园区的使用大小
OC：老年代大小
OU：老年代使用大小
MC：方法区大小
MU：方法区使用大小
CCSC:压缩类空间大小
CCSU:压缩类空间使用大小
YGC：年轻代垃圾回收次数
YGCT：年轻代垃圾回收消耗时间
FGC：老年代垃圾回收次数
FGCT：老年代垃圾回收消耗时间
GCT：垃圾回收消耗总时间

jstat -gcnew  pid //查看新生代垃圾回收统计

jstat -gcold pid // 查看老年代垃圾回收统计
```