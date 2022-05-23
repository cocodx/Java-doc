ArrayList实现了List接口，是顺序容器，元素存放的数据与放进去的顺序相同，可以放null，底层通过数组实现。

ArrayList都有一个容量capacity=10，表示底层数组的实际大小，容器内存储元素的个数不能多于当前容量。容器不足，自动扩容

#### 底层数据结构
```java
transient Object[] elementData; // non-private to simplify nested class access

/**
    * The size of the ArrayList (the number of elements it contains).
    *
    * @serial
    */
private int size;
```

#### 构造函数
```java
public ArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                            initialCapacity);
    }
}

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

public ArrayList(Collection<? extends E> c) {
    elementData = c.toArray();
    if ((size = elementData.length) != 0) {
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    } else {
        // replace with empty array.
        this.elementData = EMPTY_ELEMENTDATA;
    }
}
```

#### 自动扩容
每当向数组中添加元素时，都要去检查添加元素的个数是否会超出当前数组的长度，如果超出，数组将会进行扩容，以满足添加数据的需求。每次增长是1.5倍。Arrays.copyOf();
或者通过调用ensureCapacity方法来手动增长。