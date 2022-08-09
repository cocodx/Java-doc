##### postgresql

```sql
or cp.production_license like concat('%',#{query.codeKeywords}::text,'%')
```

##### mysql

```sql
 and userName like concat('%' ,#{userName},'%')
```

##### oracle

```sql
select * from SYS_MENU where url like concat(concat('%',#{roleName}),'%')
```
