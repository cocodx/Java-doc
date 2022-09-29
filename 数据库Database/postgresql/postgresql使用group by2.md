对act_id_进行去重，然后查询

```sql
select a.* from act_ru_actinst a inner join
(
select act_id_,max(duration_) as duration_ from act_ru_actinst
where proc_inst_id_='72ca39563fd611ed9c2d8c1645edc8d9' and  act_type_ = 'parallelGateway' and end_time_ != null  group by act_id_
) b 
on a.act_id_ =b.act_id_ and a.duration_ =b.duration_ ;
```
