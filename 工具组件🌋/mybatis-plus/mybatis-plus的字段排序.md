#### 实现效果

![image](https://user-images.githubusercontent.com/97614802/199190370-a6a2e97f-80b2-4c37-bde0-c9fb9366dd37.png)

前端需要把，排序的字典，升降序传给后台

![image](https://user-images.githubusercontent.com/97614802/199190172-d80e7803-935e-4ee8-9dba-9d347d6f9738.png)

http://IP/XXXXXXX/findPage?size=20&current=1&field=hosp_name&order=desc

根据field有没有值，是不是在定义的query包含之中的

```java
@Schema(hidden = true)
public final static List<String> FIR_FIELD2 = Arrays.asList("start_time_", "end_time_", "proc_name");
```

然后组装wrapper，拼在mybatis-plus的xml后面

```java
private LambdaQueryWrapper<FlowTaskEntity> getWrapper2(TaskQuery query) {
    LambdaQueryWrapper<FlowTaskEntity> lqw = new LambdaQueryWrapper<>();
    // 排序
    if (StringUtils.isNotEmpty(query.getField()) && StringUtils.isNotEmpty(query.getOrder()) && TaskQuery.FIR_FIELD2.contains(query.getField())) {
        if ("proc_name".equals(query.getField())) {
            lqw.last(Constants.ORDER_BY.concat(" t5.").concat(query.getField()).concat(" ").concat(query.getOrder()));
        } else {
            lqw.last(Constants.ORDER_BY.concat(" t1.").concat(query.getField()).concat(" ").concat(query.getOrder()));
        }
    } else {
        lqw.last(Constants.ORDER_BY.concat(" t1.end_time_ desc"));
    }
    return lqw;
}
```

基本上，是拼接 order by t1.xxxxx desc 这种

mapper用法

```java
IPage<FlowTaskEntity> findApplyedTaskPage(@Param(Constants.WRAPPER) LambdaQueryWrapper<FlowTaskEntity> lambdaQueryWrapper);

xml放在where标签后面
${ew.sqlSegment}
```





