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

#### 撤回功能

在用户发起之后，流程已经到审批人的用户任务节点进行审批，现在用户对此流程进行撤回操作。需要一个节点放置撤回状态，提交人的节点就可以起到作用了。
可以将当前流程的节点，退回到提交人节点，根据名称“提交人”可以获取到提交人节点，节点名称需要唯一！！！请注意，

撤回的时候，需要删除act_ru_actinst的表数据。

act_ru_actinst 表中有五条数据，依次说明：（开始节点，箭头线，提交人节点，箭头线，审批人1节点）
额外说明,在act_hi_actinst历史记录表中也有对应5条数据跟act_ru_actinst保持一致,id_值都是一样的.那么删除的时候
除了删act_ru_actinst表的id_值,act_hi_actinst表的数据也要删除.
当前最新的是审批人1节点，当要进行撤回，这时是要退回到提交人节点，关于act_ru_actinst表的数据处理.
disActivityId也就是act_id_,也就是在图上的节点id.
撤回的时候,保留开始节点的历史数据就行了.其他都删掉.act_ru_actinst和act_hi_actinst

查询act_ru_execution表中parent_id_值为当前流程实例id,获取到最新执行的子流程的executionId值.
就可以根据flowable提供的api进行交换节点.

```java
List<Execution> executionIds = runtimeService.createExecutionQuery().parentId("xxxProcInstId")).list();
```

获取到execution的id数组集合

再执行flowable提供的api，进行节点交换就行

```java
runtimeService.createChangeActivityStateBuilder()
.moveExecutionsToSingleActivityId(executionIds, disActivity.getId()).changeState();
```

![image](https://user-images.githubusercontent.com/97614802/236092288-d6cf74c7-b804-4d59-97fa-3bdcc1ec39bb.png)


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

