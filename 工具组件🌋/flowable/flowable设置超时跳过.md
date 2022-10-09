#### 超时跳过

##### 配置属性要开始定时job
1. 通过类进行配置
```java
configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres")
        .setJdbcUsername("postgres")
        .setJdbcPassword("password")
        .setJdbcDriver("org.postgresql.Driver")
        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
        .setDatabaseSchema("flowable")
        .setAsyncExecutorActivate(true)
;
```
2. 通过yml配置
```java
flowable:
  #启动定时任务JOB
    async-executor-activate: true
```

##### 画图
![image](https://user-images.githubusercontent.com/97614802/194748357-a8dbb72b-5737-46f0-949a-cea3a7236e07.png)

PT1M 表示一分钟后

PT1H 表示一小时后

PT1D 表示一天后

在xml中，边界定时事件为：
```java
<boundaryEvent id="sid-5315E7CF-3D6F-4AE3-891E-5B4FEDA909DB" attachedToRef="sid-A489D71C-4A93-40AE-949C-C48758D7A645">
    <timerEventDefinition>
        <timeDuration>PT1M</timeDuration>
    </timerEventDefinition>
</boundaryEvent>
```

在用户任务中，添加边界定时任务，还在用户任务中，添加执行监听器，捕获不到。但是在审批3中添加用户任务， 当审批3的用户审批后可以触发监听器。
