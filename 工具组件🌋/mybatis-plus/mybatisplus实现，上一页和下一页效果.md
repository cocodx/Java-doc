#### mybatisplus实现上一页和下一页效果

**不是平常做的分页，是下一页或者说更多这种；那就要前端传这个表的主键id，根据主键id的自增去过滤；
所以这也是主键id自增的动作，用来做下一页、下一页的效果**

```java
if (!ObjectUtils.isEmpty(query.getCurrent())){
    queryWrapper.gt(PmRiskSituation::getId,query.getRiskId())
            .orderByDesc(PmRiskSituation::getId)
            .last("limit 5")
    ;
}else{
    queryWrapper
            .orderByDesc(PmRiskSituation::getId)
            .last("limit 3");
}
```

