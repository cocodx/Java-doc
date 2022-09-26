#### 分组

根据key取进行分组，然后查询组中version_最大值的一行记录

```sql
select a.* from act_re_procdef a inner join  
(select key_,max(version_) as version_ from act_re_procdef t group by key_) b
on a.key_=b.key_ and a.version_ =b.version_
```
