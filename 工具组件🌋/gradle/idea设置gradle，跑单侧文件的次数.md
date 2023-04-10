#### 在IDEA中设置gradle，跑单侧的次数

https://blog.csdn.net/lantianjialiang/article/details/82811704

在junit5中，可以使用注解设置跑单侧的次数,这个注解只能加在方法上

```java
@RepeatedTest(10)
public void methodName() {
}
```

之前是有对应的命令，后面没做笔记忘记了
