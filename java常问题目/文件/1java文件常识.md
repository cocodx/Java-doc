一般读写文件需要两次数据复制，比如读文件，需要先从硬盘复制到操作系统内核，再从内核复制到应用程序分配的内存中。

操作系统运行所在的环境和应用程序是不一样的，操作系统所在的环境是内核态，应用程序是用户态，应用程序调用操作系统的功能，需要两次环境的切换，先从用户态切到内核态，再从内核态切到用户态。这种用户/内核的切换是有开销的，应尽量减少这种切换。

为了提升文件操作的效率，应用程序经常使用一种常见的策略，使用缓冲区。读文件时，即使目前只需要少量内容，但预知还会接着读取，就读取一次比较多的内容，放到读缓冲区，下次读取时，如果缓冲区有，就直接从缓冲区读，减少访问操作系统和硬盘。写文件时，先写到缓冲区，写缓冲区满了之后，再一次性调用操作系统写到硬盘。不过，在写结束时候，要记住将缓冲区的剩余内存同步到硬盘。操作系统自身也会使用缓冲区，不过，应用程序更了解读写模式。

在Java中，文件一般不是单独处理的，而是视为输入，输出（Input Output）IO

这个统一的概念是流，流有输入流和输出流。输入流是可以从中获取数据，输入流实际提供者是键盘，文件，网络等；输出流可以是实际目的地可以是显示终端，文件，网络等

Java IO的基本类大多位于Java.io中。类InputStream表示输入流。OutputStream可以表示输出流。FileInputStream可以表示文件输入流，FileOutputStream可以表示文件输出流

基本的流按照字节读写，没有缓冲区，这不方便使用。java解决这个问题的方法是使用装饰器模式，引入了很多装饰类，对基本的流增加功能，以方便使用。一般一个类只关注一个方面，实际使用的时候，经常会需要多个装饰类。

##### 序列化和反序列化
序列化：将内存中的Java对象保存到一个流中，反序列化就是将流中恢复Java对象到内存。序列化和反序列化主要有两个用处：一是对象保持持久化，而是网络远程调用，用于传递和

Java主要通过接口Serializable和ObjectInputStream和ObjectOutputStream。来提供对序列化的支持。去诶单：性能低，浪费空间，Java特有的技术，不能与其他语言交互。

##### File
关于文件路径，文件元数据，文件目录，临时文件，访问权限管理等，Java使用File这个类来表示。

##### Reader/Writer
以InputStream和OutputStream为基类的流基本都是以二进制形式处理数据的。不能够方便的处理文本文件，没有编码的概念，能够方便地按字符处理文本数据的基类是Reader/Writer，它也有很多子类。

将InputStream和OutputStream，转换为Reader和Writer的子类是，InputStreamReader和OutputStreamReader。

