#### 1.1 sync的使用
* 一把锁只能同时被一个线程获取，没有获得锁的线程只能等待
* 每个实例都对应有自己的一把锁（this），不同实例之间互不影响；例外：锁对象是*.class以及synchronized修饰的是static方法的时候，所有对象公用同一把锁
* sync修饰的方法，无论方法是否正常执行完毕，还是抛出异常，都会释放锁

#### 1.2 对象锁
包括方法锁（默认对象锁为this，当前实例对象）和同步代码块锁（自己指定的锁对象）

看**monitorenter**和**monitorexit**
这两个指令，会让对象在执行时，使其锁计数器加1或者减1，每一个对象 