#### mybatis plus使用distinct去重

使用QueryWrapper支持，LambdaQueryWrapper不支持distinct去重！

```java
public List<ActRuTask> findListByDistinctProcDefIdAndApprovalId(String userId){
  QueryWrapper<ActRuTask> queryWrapper = new QueryWrapper<>();
  queryWrapper.select("DISTINCT proc_def_id_");
  queryWrapper.eq("assignee_",userId);
  return baseMapper.selectList(queryWrapper);
}
```
