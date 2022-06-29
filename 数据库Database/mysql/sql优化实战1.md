#### 数据统计页面查询慢
##### 统计附件大小分布
运行方式：定时器，实际的效果是15s一次  
```sql
explain
select
	l.corp_id,
	SUM( case when l.att_size <= 1048576 then 1 else 0 end ) as
size_less_1m,
	SUM( case when l.att_size > 1048576 and l.att_size < 5242880 then 1 else 0 end ) as size_less_5m,
	SUM( case when l.att_size >= 5242880 and l.att_size < 10485760 then 1 else 0 end ) as size_less_10m,
	SUM( case when l.att_size >= 10485760 then 1 else 0 end ) as size_more_10m,
	SUM(l.att_size) as sumSize
from
	logreport.log_send_mail_06 l
where
	l.log_time >= '2022-06-29 10:00:00'
	and l.log_time <= '2022-06-29 10:59:59'
group by
	l.corp_id;
```

***
![1369fa72a5d7eeab4f5f48d10e70105](https://user-images.githubusercontent.com/97614802/176398622-72e774e7-b093-4717-9679-b659d747c9eb.png)

这个表

时间加索引，效果有用吗？  

这个查询时间差别就在于今天零点开始，时间变长，平均查询一次 时间8s到30s   

![image](https://user-images.githubusercontent.com/97614802/176399025-519015ab-c84d-4dd2-bc35-c7775e4b4e3d.png)

标准版是把log_time改成send_time，send_time是有用到索引  
标准版 十分钟一次  

