#### 在query的实体类中定义

query：flowableTaskQuery
类名：flowableTask

定义set集合范围和传来的field字段和order字段
```java
public final static List<String> FIELD_LIST = Arrays.asList("create_time","update_time","yyyy");
//可排序字段
private String field;
//desc asc
private String order;
```

在queryWrapper中末尾加上判断

```java
// 排序
if (StringUtils.isNotBlank(query.getField()) && StringUtils.isNotBlank(query.getOrder())
    && flowableTaskQuery.FIELD_LIST.contains(query.getField())) {
    lqw.last(Constants.ORDER_BY.concat(" ").concat(query.getField()).concat(" ").concat(query.getOrder()));
} else {
    lqw.orderByDesc(flowableTask::getUpdateTime);
}
```
