spring的循环依赖，里面有3个Map

```java
/** 一级缓存Cache of singleton objects: bean name to bean instance. */
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

/** 二级缓存Cache of early singleton objects: bean name to bean instance. */
private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>(16);

/** 三级缓存Cache of singleton factories: bean name to ObjectFactory. */
private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
```

一级缓存，我清楚，key是beanName，value是已经new好的对象，存在getBean的话，直接返回。没有的话，再去二级缓存查找。earlySingletonObjects，如果二级缓存也没有，再到三级缓存去找singletonFactories。

![image](https://user-images.githubusercontent.com/97614802/196312072-544e0bd2-37bb-4ad5-8757-e3fbd3ab21dc.png)

遇到循环依赖了
