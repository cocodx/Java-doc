##### postgresql和mysql的group by不一样
必须是select ... from tableName where ... group by ...

select 筛选的字段，必须和 group by的字段 一致，才不会报错。

举例说明

```sql
select id, user_name, count(class_id) from sys_user GROUP BY id,user_name,class_id;
```

##### postgresql的group by 主键id是不会有问题的【仅仅group by id】

```sql
SELECT * FROM public.sys_user where 1=1 group by id ;
```

![image](https://user-images.githubusercontent.com/97614802/183057428-c81b290a-81b2-4c19-a75a-7107bea1d2e3.png)
