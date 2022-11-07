#### postgresql中查询某字段，不等于某值的时候。

要注意当这个字段是空的时候，是查不出来的。需要OR一下，这个字段是null。例子如下：

```xml
t1.delegation_ != 'RESOLVED'::text OR t1.delegation_ IS NULL
```

![image](https://user-images.githubusercontent.com/97614802/200223054-12774569-d997-41c1-a3d1-57a52d24a8a5.png)
