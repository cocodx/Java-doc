反射：将类的各个部分封装成其他对象，这就是反射机制。

作用：在运行时分析类的能力。
在运行时查看对象，例如，编写一个toString方法供所有类使用。
实现通用的数组操作代码
利用Method对象，这个对象很像C++中的函数指针。
```java
Class aClass = Class.forName("com.ganggang.alo.search.TestR");
Object m = aClass.newInstance();
```

Spring是怎么使用反射的呢