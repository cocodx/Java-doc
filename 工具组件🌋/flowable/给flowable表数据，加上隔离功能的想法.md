租户id，医院id表设置

id 主键

租户id

医院id

act_de_model的id主键。

...

其他的都略过

```java
query
if(是超级管理员){
    list = 查询所有用户。
}
if(是租户){
    query.setTId(CurrentUtils.getUserId)
}else if(是普通用户){
    query.setTId(CurrentUtils.getCorpCode)
    query.setHospId(CurrentUtils.getHospId);
}
```

```java
当用户去搜索的时候，先到这个表去查，到list集合

List<String> modelIdList

if(modelIdList != null){
    查询其他表的时候，modelId in modelIdList
}else{
    //如果在那个表是空的，有哪几种情况呢
    1. 确实没有的话，没有数据，modelId in (kong) 那么就不在act_de_model表去查了
    2. 如果有的话，就是正常情况了，有建模型，直接in就好了。
    3. 这样就筛选了。
}
```
