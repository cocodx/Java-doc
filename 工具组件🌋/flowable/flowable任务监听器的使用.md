#### the type of Task listener's 

```java
create
assignment
complete
delete
```

I have setted listener for the complete task 

then in the parts of staring process code,adding complete task logic code.because I know the submitter's node id and name

like this above image,i know it's id and name ,so i can enquery this row data

![image](https://github.com/cocodx/Java-doc/assets/97614802/f24304f4-cdf2-4bec-bb6d-26f0d468cf90)

we can use flowable providing api service or use our own mapper methods. I will give you an example
```java
Task task = taskService.createTaskQuery().processInstanceId("procInstId").taskDefinitionKey("nodeId").singleResult();
taskService.complete(task.getId());
```
This listener we need to implement TaskListener.There is point that don't using complete in the parts of method.it can cause infinit loop（死循环）.

```java
public Class OurOwnTaskListener implement TaskListener{
  *****
  *****
  *****
}
```
