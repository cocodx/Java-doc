#### 逻辑删除

在类的表示删除状态的字段上添加注解
```java
@TableLogic(value = "1",delval = "0")
private Integer showStatus;
```