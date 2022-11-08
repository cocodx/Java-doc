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

**边缘事件的监听器，在Flowable的UI里面是配置不了，直接写在xml即可。**

![image](https://user-images.githubusercontent.com/97614802/200502546-438fba13-9568-4673-84ef-879a1afd8a14.png)

在xml中，边界定时事件为：
```java
<boundaryEvent id="sid-5315E7CF-3D6F-4AE3-891E-5B4FEDA909DB" attachedToRef="sid-A489D71C-4A93-40AE-949C-C48758D7A645">
    <timerEventDefinition>
        <timeDuration>PT1M</timeDuration>
    </timerEventDefinition>
</boundaryEvent>
```

在用户任务中，添加边界定时任务，还在用户任务中，添加执行监听器，捕获不到。但是在审批3中添加用户任务， 当审批3的用户审批后可以触发监听器。

##### 注意，在测试的时候，开启流程要阻塞的时间大于设置的时间，不然没有jvm进程去跑定时任务，那个超时就不生效了
```java
@Test
    public void testRunProcess() throws InterruptedException {
        ProcessEngine processEngine = configuration.buildProcessEngine();

        //我们需要通过RuntimeService来启动流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //构建流程实例
        Map<String,Object> variables = new HashMap<>();
        variables.put("skip",true);
        variables.put("_FLOWABLE_SKIP_EXPRESSION_ENABLED", true);
        //启动流程实例
        ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("myTestKey", variables);
//        ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKeyAndTenantId("myTestKey1234", variables, "user1");
        System.out.println("holidayRequest.getProcessDefinitionId(); = " + holidayRequest.getProcessDefinitionId());
        System.out.println("holidayRequest.getActivityId(); = " + holidayRequest.getActivityId());
        System.out.println("holidayRequest.getId(); = " + holidayRequest.getId());
        Thread.sleep(1000*60*2);
    }
```
