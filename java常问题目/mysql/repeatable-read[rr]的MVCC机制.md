
在RR级别下，你这个事务读一条数据，无论读多少次，都是一个值，别的事务修改数据之后，哪怕提交了，你也是看不到人家修改的值，这就避免了不可重复读的问题。

避免不可重复读问题
sql展示
```java
//查出来的这条数据，是永远不会变的
select * from xxxx where id=68;
```
![image](../../images/Snipaste_2022-05-09_02-31-37.png)

避免幻读
sql展示
```java
//查出来的这批数据，是永远不会变的，不会多，也不会少
select * from xxxx where id>10;
```
![image](../../images/Snipaste_2022-05-09_02-39-54.png)