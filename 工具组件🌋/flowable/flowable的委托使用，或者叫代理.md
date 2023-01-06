#### flowable的委托，实际上就是：流程代理人，当前任务的人将此审批动作委托给其他人处理。

flowable的api使用：
```java
Delegates the task to another user. This means that the assignee is set and the delegation state is set to DelegationState.PENDING. If no owner is set on the task, the owner is set to the current assignee of the task.
Params:
taskId – The id of the task that will be delegated. userId – The id of the user that will be set as assignee.
Throws:
FlowableObjectNotFoundException – when no task exists with the given id.

void delegateTask(String taskId, String userId);
```

调用此方法之后，
ACT_RU_TASK表中的字段变化为：
* DELEGATION_字段的值变化为PENDING，表示此任务为正在执行的委托任务；
* OWNER_字段表示为委托人；
* ASSIGNEE_表示当前任务处理人；

![image](https://user-images.githubusercontent.com/97614802/210962798-aab19ae0-32e4-4096-9585-58d56d90c3d7.png)

#### 解决委托

```java
/**
 * 根据taskId解决被委托的任务并进行到下一个节点，动态传值设置变量
 */
public static void resolveTask(String taskId, Map<String, Object> variables) {
  //根据taskId提取任务
  Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
  if (task.getOwner() != null && !task.getOwner().equals("null")) {
      DelegationState delegationState = task.getDelegationState();
      if (delegationState.toString().equals("RESOLVED")) {
          System.out.println("此委托任务已是完结状态");
      } else if (delegationState.toString().equals("PENDING")) {
          //如果是委托任务需要做处理
          taskService.resolveTask(taskId);
          taskService.complete(taskId, variables);
      } else {
          System.out.println("此任务不是委托任务");
      }
  }
}
```
此时的数据库：

DELEGATION_字段的值变为RESOLVED，表示此任务被解决的委托任务：

![image](https://user-images.githubusercontent.com/97614802/210963444-060c00cd-9453-466a-bc32-14216597a244.png)

#### 光完成委托没有用
flowable、Activiti都是外国人写的，外国人与我们想法不一样，他们认为委托任务必须有解决委托这一步骤，当解决委托后，流程并不是进行到下一个节点，而是需要被委托人有
完成任务操作时，方可进行到下一步。而中国式需求中大多都是解决委托就是完成任务，需要解决这个问题的话可以在调用解决委托后执行一个完成任务代码操作。

**委托任务如果不解决委托将无法进行到一节点，完成任务时将直接抛出异常**


