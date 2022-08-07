![image](../../images/Snipaste_2022-08-08_00-44-02.png)

第一次，把查询条件带过去，会写入session中。

第二次，不带呢，在调用dao之前，尝试从session中获取，上次存的参数值。

具体代码如下：
![image](../../images/Snipaste_2022-08-08_00-47-23.png)