java中atomic类的实现，其底层实现是（compile and swap ）CAS操作是原子性的，while循环比较并交换，为了防止ABA问题
使用了AtomicStampedReference类来保证version版本号。

限制：
当一个线程长时间自旋不成功，会给CPU带来非常大的执行开销。并且只能保证一个共享变量的原子操作
![image](../images/CAS.png)