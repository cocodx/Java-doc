HashMap 线程不安全，运行速度快
允许存储null

HashTable 线程安全，运行速度慢
不允许存储null

HashTable里面都添加了synchronized关键字保证线程同步，相对而言，HashMap性能会高一些。
hashMap是对Map接口的实现，HashTable实现了Map接口和Dictonary抽象类。  

HashMap的初始容量为16，hashtable的初始容量为11，两者的填充因子为0.75。  

hashMap扩容是2倍，hashtable扩容是2倍+1

List,Set,Map等接口是否都继承子接口Map
List，Set继承Collection接口
Map独立接口。不继承Collection接口

Set和Map关系，Set集合底层就是使用Map集合。

你常见的集合类有哪些，都有什么方法？
常见的，使用评率高的
List ArrayList
Set HashSet
Map HashMap

Collection List Set
常见方法 add size iterator

Map常见方法
put get keySet EntrySet size

Properties
常见方法
getProperty setProperty laod store