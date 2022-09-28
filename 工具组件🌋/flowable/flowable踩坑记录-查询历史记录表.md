#### flowable踩坑记录

##### mybatis的sql语句，去联表查询sql语句报错，使用api确可以查出来！！！

以下代码，使用api查询出来了
```java
GetProcessInstanceCommentsCmd getProcessInstanceCommentsCmd = new GetProcessInstanceCommentsCmd(processInstanceId);
List<org.flowable.engine.task.Comment> comments = managementService.executeCommand(getProcessInstanceCommentsCmd);
List<CommentEntityImpl> commentEntities = new ArrayList<>();
comments.forEach(item -> {
    CommentEntityImpl commentEntity = new CommentEntityImpl();
    BeanUtils.copyProperties(item, commentEntity);
    commentEntities.add(commentEntity);
});
return commentEntities;
```

##### mybatis语句
```java
SELECT t1.id_,
       t1.type_,
       t1.time_,
       t1.user_id_,
       t2.first_,
       t1.task_id_,
       t1.proc_inst_id_,
       t1.message_
FROM act_hi_comment t1
         INNER JOIN act_id_user t2 ON t1.user_id_ = t2.id_
WHERE t1.proc_inst_id_ = #{pInsId}::text AND t1.action_ = 'AddComment'
ORDER BY t1.time_ ASC
```

![image](https://user-images.githubusercontent.com/97614802/192767331-e3ef8800-00d2-4570-a34b-0662f4b6678b.png)
