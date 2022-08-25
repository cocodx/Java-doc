#### 字符流
使用字节流读取中文时，会出现显示的小问题。一个中文如果是GBK占用两个字节，如果是UTF-8占用三个字节

使用字符就可以一次读写一个字符，不管是中文还是英文还是数字

##### Reader抽象类
Reader字符输入流，最顶级的父类，抽象类

共性的成员方法，read，read(byte[])，close()

读取的方式，真字符流一模一样，只是读取的内容不一样是，读字符

Reader、Writer【close关闭此流，先刷新它】

构造方法：

FileWriter(File file)根据给定的File对象构造一个FileWriter

FileWriter(String fileName)根据给定的文件名构造一个FileWriter对象。

参数：写入数据的目的地
    
    String fileName：文件的路径

    File file：是一个文件    

字符输出流的使用步骤（重点）：

1. 创建FileWriter对象，构造方法中绑定要写入数据的目的地
1. 使用FileWriter中的方法write，把数据写入到内存缓冲区中（字符转换为字节的过程）
1. 使用FileWriter中的方法flush，把内存缓冲区中的数据，刷新到文件中
1. 释放资源（会先把内存缓冲区中的数据刷新到文件中）

flush和close区别：
* flush：刷新缓冲区，流对象可以继续使用
* close：先刷新缓冲区，然后通知系统释放资源，流对象不可以再被使用了。