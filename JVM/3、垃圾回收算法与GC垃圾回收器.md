#####标记清除算法
1 效率问题
2 空间问题（标记清除后产生大量的碎片）




###### 复制算法
它可以将内存分为大小相同的两块，每次使用其中的一块。当这一块的内存使用完之后，就将还存活的对象复制到另一块。然后再把使用的空间一次清理掉。每次的内存回收都是对内存区间的一半进行回收。
就是年轻代用的，老年代不用的，90%的就是朝生夕死的。只要标记10%。老年代大多数都是存活的。

###### 标记整理算法
根据老年代的特点，标记算法，标记过程与 标记-清除 一样，但后续步骤不是直接对可回收对象回收，而是让所有存活对象向一端移动，然后直接清理掉端边界以外的内存。

###### 分代收集算法
新生代和老年代

#### 垃圾收集器
##### Serial收集器(-XX:+UseSerialGC -XX:+UseSerialOldGC)[Serial,SerialOld。新生代有Serial，老年代有SerialOld] 
串行收集
Serial 串行收集器，单线程，在进行垃圾收集工作的时候，必须暂停其他所有的工作线程（Stop the world），直到它收集结束。
简单而高效。
Serial在新生代是使用复制算法，老年代采用标记-整理算法
在多核计算器就不太合适了。

##### ParNew收集器(-XX:+UseParNewGC)[ParNew。新生代有ParNew] 
ParNew，默认的收集线程数跟CPU核数相同，也可以用参数-XX：ParallelGCThreads 指定收集线程数。
新生代是使用复制算法，老年代采用标记-整理算法

##### Parallel收集器(-XX:+UseParallelGC(年轻代),- XX:+UseParallelOldGC(老年代)[Parallel,ParallelOld。新生代有Parallel，老年代有ParallelOld] 
Parallel Scavenge 收集器类似于ParNew 收集器，是Server 模式（内存大于2G，2个cpu）下的 默认收集器，那么它有什么特别之处呢？
所谓吞吐量就是CPU中用于运行用户代码的时 间与CPU总消耗时间的比值。
Parallel Scavenge收集器提供了很多参数供用户找到最合适的停顿 时间或最大吞吐量，如果对于收集器运作不太了解的话，可以选择把内存管理优化交给虚拟机去完 成也是一个不错的选择。

##### CMS收集器
初始标记，并发标记，重新标记，并发清理

* 并发清理：在这个时候产生的新垃圾对象（浮动垃圾），不会去进行清理，下一次gc清理。
* 对CPU资源敏感（会和服务抢资源）
* 它使用的回收算法-**标记清除**算法会导致收集结束有大量空间碎片产生，可以通过参数：-XX:UseCMSCompactAtFullCollection   可以让jvm执行完标记清楚后再做整理
* 第一次full gc，还没执行结束，又来了一次触发full gc。并发阶段收集失败了，整个concurrent mode failure，此时会进入stop the world，用serial old垃圾收集器来回收。

CMS的相关参数：
1. -XX:+UseConcMarkSweepGC：启用cms 
1. -XX:ConcGCThreads：并发的GC线程数 
1. -XX:+UseCMSCompactAtFullCollection：FullGC之后做压缩整理（减少碎片） 
1. -XX:CMSFullGCsBeforeCompaction：多少次FullGC之后压缩一次，默认是0，代表每次 FullGC后都会压缩一次 
1. -XX:CMSInitiatingOccupancyFraction: 当老年代使用达到该比例时会触发FullGC（默认 是92，这是百分比） 
1. -XX:+UseCMSInitiatingOccupancyOnly：只使用设定的回收阈值(- XX:CMSInitiatingOccupancyFraction设定的值)，如果不指定，JVM仅在第一次使用设定 值，后续则会自动调整 
1. -XX:+CMSScavengeBeforeRemark：在CMS GC前启动一次minor gc，目的在于减少 老年代对年轻代的引用，降低CMS GC的标记阶段时的开销，一般CMS的GC耗时 80%都在 remark阶段

年轻代用的parNew，老年代用的CMS收集器

##### G1收集器

整堆收集器：


#### 来点真实的
项目中，用的收集器一般是ParNew+CMS.自测过使用G1替换前者，发现访问javaweb项目都慢很多。
jstat -gc pid 毫秒数
去查看，FGC和YGC次数。
