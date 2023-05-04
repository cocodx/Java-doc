#### 自动跳过用户任务

##### flowable 用户任务，跳过的属性设置

1. 用户任务的名称 **提交人**
2. 任务派遣的值 **${initiator}**
3. 跳过表达式 **${initiator==''}**

![image](https://user-images.githubusercontent.com/97614802/236089733-a55a234b-05b9-4f88-be5a-24ee1933addc.png)

根据用户任务的名称，“提交人”就可以获取到user task的Activity。任务派遣的值设置${initiator}，initiator是一个变量，可以传值。跳过表达式为
${initiator==''}，作用是当传值initiator是一个空串，在允许跳过的情况下，流程直接跳过去了。

所以在发起流程的controller中，添加跳过启动参数

```java
Map<String, Object> paramsVariables = params.getVariables();
params.getVariables().put("_FLOWABLE_SKIP_EXPRESSION_ENABLED", true);
params.getVariables().put("initiator", "");

....
runtimeService.createProcessInstanceBuilder().name("xxxxx").variables(params.getVariables())......start();
```
以上就可以实现用户任务自动跳过的功能。

##### 添加提交人的作用

结合流程撤回功能，以及重新发起功能进行使用。


##### 在开启任务的时候，在启动变量传入以允许跳转的属性
```java
//开启跳转
variables.put("_FLOWABLE_SKIP_EXPRESSION_ENABLED", true);
```

这样跳过表达式才能生效啊。

![image](https://user-images.githubusercontent.com/97614802/194750704-fe162dba-6248-4c0d-b342-be2b4bc497cd.png)

当再传入variables.put("skip", true);

就跳过这个任务了。

![image](https://user-images.githubusercontent.com/97614802/194750763-4f05ab2e-7c70-4df5-8a32-e0ee61991658.png)

后面驳回的时候，再传入skip为false，就跳不了。

