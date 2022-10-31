#### easypoi的api调用报null异常

tmd的，真是自己坑自己，看了半天，一个bug还没解决呢

![image](https://user-images.githubusercontent.com/97614802/198823423-a0d28954-62af-4511-9302-ab4dff3adf40.png)
![image](https://user-images.githubusercontent.com/97614802/198823433-c8255eb3-f8e0-401b-a6fc-b4d05d10592a.png)

打断点，这是空的

![image](https://user-images.githubusercontent.com/97614802/198823440-3ff10509-7f03-4836-b884-ecaa41d0176c.png)

之前commons模块里，是有引入easypoi，这个easypoi本身就是封装apache的poi。

当我又在commons的pom.xml里面，引入apache的poi，覆盖了兄弟。完蛋

![image](https://user-images.githubusercontent.com/97614802/198823615-ce9c68b0-cea7-4113-842b-3d327b3feabe.png)

```xml
<dependency>
    <groupId>cn.afterturn</groupId>
    <artifactId>easypoi-base</artifactId>
    <version>${easypoi.version}</version>
</dependency>
```

点到easypoi-base里面去，

![image](https://user-images.githubusercontent.com/97614802/198823709-321c0f82-84fc-4380-8e03-1c9364a9e0b6.png)

不过，倒是对追踪stack。有心得了。
