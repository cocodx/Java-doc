#### wait和notify简介
* Object.wait()----暂停一个线程
* Object.nofify()----唤醒一个线程

***

* 想要使用这两个方法，我们需要先有一个对象Object。
* 在多个线程之间，我们可以通过调用同一个对象的wait()和notify()来实现不同的线程之间的可见

##### 对象控制权（monitor）