HashSet和HashMap放在一起讲解，前者是对后者做了一层包装，也就是说HashSet里面有一个HashMap(适配器模式)。

HashMap实现了Map接口，允许key为null的元素，也允许插入value为null的元素，除了未实现同步之外，其余跟Hashtable大致相同；跟TreeMap不同，该容器不保证元素顺序，根据需要该容器可能会对元素重新hash，元素的顺序会被重新打散，bitmap也是一样，用了多个hash算法减少hash冲突。

#### Java8 HashMap
组成：数组+链表+红黑树
jdk7，当我们定位到具体的下标，需要顺着链表一个个比较下去才能找到我们需要的，时间复杂度O(n),取决于链表的长度，为O(n)
为了降低这个开销，Java8中，当链表中达到了8个时，会将链表转成红黑树，在这些位置进行查找的时候可以降低时间复杂度为O(logN)

#### 数组扩容
2n