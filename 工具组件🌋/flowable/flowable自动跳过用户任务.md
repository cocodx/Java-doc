#### 自动跳过用户任务

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

