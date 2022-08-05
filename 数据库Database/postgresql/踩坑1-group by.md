##### postgresql和mysql的group by不一样
必须是select ... from tableName where ... group by ...

select 筛选的字段，必须和 group by的字段 一致，才不会报错。

举例说明

```sql
select id, user_name, count(class_id) from sys_user GROUP BY id,user_name,class_id;
```
