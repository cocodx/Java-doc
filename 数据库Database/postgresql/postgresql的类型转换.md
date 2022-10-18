#### 类型转换使用

场景：在左连接的时候，A表的条件assignee_是varchar，而B表的条件是bigint，这样去查的就会报错

![image](https://user-images.githubusercontent.com/97614802/196321022-dca230fc-052f-4aed-9f9b-3ec211931846.png)

修改后
```xml
SELECT t2.user_login_account
        FROM act_ru_task t1
                 LEFT JOIN sys_user t2 ON t1.ASSIGNEE_ = t2.id::text
        WHERE t1.PROC_INST_ID_ = #{procInstId}
```

::text
