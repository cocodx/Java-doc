#### flowable监听任务

实现TaskListener org.flowable.engine.delegate.TaskListener

##### 当一个节点，集合绑定多个用户，并行生成任务，会依次调用CreateTaskListener

在flowable的ui设置界面，绑定监听器

委托表达式中，${}里面就是对应spring容器的bean

![image](https://user-images.githubusercontent.com/97614802/200222603-960f0d1d-1d77-4c6f-bbc7-e942f65d8e19.png)
![image](https://user-images.githubusercontent.com/97614802/200222651-07a7146c-2b11-4073-9401-f9e388cb22b9.png)

具体代码如下

```java
@Slf4j
@Service
public class CreateTaskListener implements TaskListener {

    //任务参数，任务监听器ui界面配的，名称必须为ui界面一致。
    private Expression expression;

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("消息提醒，流程定义id：{}",delegateTask.getProcessDefinitionId());
        log.info("消息提醒，assignee：{}",delegateTask.getAssignee());
        String paramName = (String)expression.getValue(delegateTask);
    }
}
```

在一个节点，如果有多个用户并行生成任务，会依次回调CreateTaskListener，一个接一个

![image](https://user-images.githubusercontent.com/97614802/200213597-047715b5-519a-418e-ba06-2020b65b5a93.png)

##### 转办给某用户，是否触发CreateTaskListener


#### 在监听任务中，不能把某个service的事务标记成never，flowable会抛出异常
