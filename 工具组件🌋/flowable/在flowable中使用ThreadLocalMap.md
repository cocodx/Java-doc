#### flowable中使用ThreadLocal，多线程共享变量，线程安全。

```java
public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
```

背景介绍：在用户发起流程，需要在事务中心，添加一条数据，创建人是发起流程用户的id。在表中获取不到，此时历史流程实例表act_hi_procinst中尚无数据。

在调试Flowable的任务监听器，发现没有数据。任务竟然是先流程表数据创建的。所以，在这里使用ThreadLocal。在controller里，存入当前发起的用户的id，

在触发的监听器那里去取。

![image](https://user-images.githubusercontent.com/97614802/206620055-f4fe527f-0d15-44a5-b78d-dfe778392f04.png)

![image](https://user-images.githubusercontent.com/97614802/206620067-0c503a9b-cbdc-43aa-b945-e10f46ecc1c4.png)

