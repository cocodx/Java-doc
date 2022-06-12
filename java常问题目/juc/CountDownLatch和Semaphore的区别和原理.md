#### CountDownLatch和Semaphore的区别和原理

CountDownLatch表示计数器，可以给CountDownLatch设置一个数字，一个线程调用调用CountdownLatch的await()将会阻塞，
其他线程可以调用CountdownLatch的countDown()方法对CountdownLatch中的数字减1，当数字减成0后，所有wait的线程都将被唤醒。
对应的底层原理是，调用await()方法的线程会利用aqs排队，一旦数字为0，则会将aqs中排队的线程依次唤醒。

Semaphore表示信号量，可以设置许可的个数，表示同时允许最多多少个线程使用该信号量，通过acquire()来获取许可，如果没有
许可可用则线程阻塞，并通过AQS来排队，可以通过release()方法来释放许可，当某个线程释放了许可后，会从AQS中正在排队的第一个
线程开始一次唤醒，直到没有空闲许可。