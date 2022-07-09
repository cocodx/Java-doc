1. 查询语句中不要使用select * 
1. 尽量减少子查询，使用关联查询（left join，right join，inner join）替代
1. 减少使用in或者not in，使用exists，not exists或者关联语句替代
1. or的查询尽量使用union或者union all代替（在确认没有重复数据或者不用剔除重复数据时，union all会更好）
1. 应尽量避免在 where 子句中对字段进行 null 值判断，否则将导致引擎放弃使用索引而进行全表

扫描，如： select id from t where num is null 可以在num上设置默认值0，确保表中num列没有
null值，然后这样查询： select id from t where num=0