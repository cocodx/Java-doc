#### wait和notify简介
* Object.wait()----暂停一个线程
* Object.nofify()----唤醒一个线程

***

* 想要使用这两个方法，我们需要先有一个对象Object。
* 在多个线程之间，我们可以通过调用同一个对象的wait()和notify()来实现不同的线程之间的可见

##### 对象控制权（monitor）
在使用wait和notify之前，我们需要了解对象的控制权（monitor）。在java中任何一个时刻，对象的控制权只能对一个线程拥有。如何理解控制权？
```java
public class Test1{

    public static void main(String[] args) {
        Object o = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
```

直接执行，我们将会得到一下异常：
```java
Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at com.liugang.juc.c1.waitandnotify.Test1$1.run(Test1.java:15)
	at java.lang.Thread.run(Thread.java:748)
```

出错的代码在：Object.wait()
* 无论是执行对象的wait、notify还是notifyAll方法，必须保证当前运行的线程取得该对象的控制权（monitor）
* 如果在没有控制权的线程里执行对象的以上三种方法，就会报java.lang.IllegalMonitorStateException
* JVM基于多线程，默认情况下不能保证运行时线程的时序性

so，我们可以通过同步锁获得对象控制权
```java
public class Test1{

    public static void main(String[] args) {
        Object o = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
```
得到一下结论：
* 可以调用对象的wait和notify方法，需要先取得对象的控制权
* 可以使用synchronized(object)来取得object对象的控制权

```java
public class ThreadTest {

    private final Object flag = new Object();

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        ThreadA threadA = threadTest.new ThreadA();
        threadA.start();

        ThreadB threadB = threadTest.new ThreadB();
        threadB.start();

    }

    class ThreadA extends Thread{
        @Override
        public void run() {
            synchronized (flag){
                for (int i = 1; i <=100; i=i+2) {
                    flag.notify();
                    System.out.println(i);
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    class ThreadB extends Thread{
        @Override
        public void run() {
            synchronized (flag){
                for (int i = 2; i <=100; i=i+2) {
                    flag.notify();
                    System.out.println(i);
                    if (i==100){
                        break;
                    }
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}

```