强弱软虚

1.强引用

平时敲代码就是用强引用
```java
HashMap<String, Object> hashMap = new HashMap<>();
```
在程序内存不足（OOM）的时候也不会被回收

1.软引用

```java
SoftReference<String> wrf = new SoftReference<>(new String("str"));
```
创建缓存的时候，创建的对象放入缓存中，当内存不足的时候，jvm会回收早先创建的对象。

1.弱引用

```java
WeakReference<String> wrf = new WeakReference<>(new String("11111"));
```
弱引用只要JVM回收器发现了它，就回收它。

1.虚引用
虚无缥缈