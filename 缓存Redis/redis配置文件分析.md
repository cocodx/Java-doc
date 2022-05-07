
节省服务端的连接数。
查看服务端的连接数。
```java
config get maxClients
```
```java
timeout 60 //当client与server端进行连接，超过60秒就断开。
tcp-keepalive 300 //当client与server端，tcp连接，300秒都没发送ack标识，就断开连接。
```

修改linux内核参数，防止在做rdb的时候，double的内存不够用。
```java
vm.overcommit_memory=1
```
0:检查是否有足够的可用内存供进程使用，有则允许申请，否则，申请失败，返回错误
1：表示内核允许分配所有的物理内存，而不管当前的内存状态如何；
2：表示内核允许分配超过所有物理内存和交换空间总和的内存。
