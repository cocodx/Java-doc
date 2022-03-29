JVM的生命周期，开始运行结束

#### java虚拟机的启动
Java虚拟机的启动是通过引导类加载器bootstrap class loader，创建一个初始类initial class来完成的，这个类是由虚拟机的具体实现指定的（是哪个虚拟机）。 Hotspot，IBM J9VM。

