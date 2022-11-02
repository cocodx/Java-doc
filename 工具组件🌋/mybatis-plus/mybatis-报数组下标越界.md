#### 报错现象

![image](https://user-images.githubusercontent.com/97614802/199425339-05c9386a-314a-41ea-9390-7e113b28ee55.png)

sql没有写错，百度了一下，发现是 mybatis在帮我们转实体的时候，我使用lombok构造了有参构造器，没有无参构造器，导致的

![image](https://user-images.githubusercontent.com/97614802/199425537-2b0e867f-3dbb-42d4-8070-35c93d45bff5.png)

加上无参再试试

![image](https://user-images.githubusercontent.com/97614802/199425603-5724f3c3-59e6-4e09-9bd9-6f0ed23bc443.png)

但是这样还会产生错误

![image](https://user-images.githubusercontent.com/97614802/199426682-762670a8-3531-4061-ba90-929500d6f838.png)

缺少全参构造器导致，再在实体类上加上 @AllArgsConstructor

这样就不会了。
