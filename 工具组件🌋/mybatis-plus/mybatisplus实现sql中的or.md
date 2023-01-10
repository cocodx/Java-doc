#### 实现sql如下

```sql
where id!=10 
and type = 1 and (curWorkPlanId = 1 or originalPlanId = 1)
```

从type=1后面开始，展示mybatisplus的写法

changeQueryWrapper 是 LambdaQueryWrapper

```java
changeQueryWrapper.and(
wrapper-> 
wrapper
.eq(WmScheduleApplyExt::getOriginWorkPlanId,workPlanId)
.or()
.eq(WmScheduleApplyExt::getCurrentWorkPlanId,workPlanId
)
);
```
