#### FileInputStream
用来读取磁盘中文件到内存中，输入流

![image](https://user-images.githubusercontent.com/97614802/186651764-14337db9-6463-4d2c-846d-55fae6c9924e.png)

```java
public static void main(String[] args)throws Exception {
  FileInputStream fileInputStream = new FileInputStream("D:\\a.txt");
  byte[] bytes = new byte[2];

  int len = fileInputStream.read(bytes);
  System.out.println(len);
  System.out.println(new String(bytes,0,len));

  len = fileInputStream.read(bytes);
  System.out.println(len);
  System.out.println(new String(bytes,0,len));

  len = fileInputStream.read(bytes);
  System.out.println(len);
  System.out.println(new String(bytes,0,len));
}
```

new byte[2];
![image](https://user-images.githubusercontent.com/97614802/186653232-9964b41e-fa9e-4132-8c20-84c3f9b66a22.png)

数组起到缓冲作用，存储读取到多个字节

new byte[5]:一次读取5个字节

ABCDE

一般数组长度定义为1024，就是1KB，或者是1024的整数位，

int的返回值，每次读取到的有效字节数

以上读取是一个重复的过程，可以使用循环优化，所以使用while循环，while循环结束的条件，读取到-1结束。

#### 练习，复制文件
```java
public static void main(String[] args)throws Exception {
    FileOutputStream fileOutputStream = new FileOutputStream("E:\\11.xlsx");
    FileInputStream fileInputStream = new FileInputStream("F:\\11.xlsx");
    byte[] bytes = new byte[1024];
    int len;
    while (( len = fileInputStream.read(bytes))!=-1){
        fileOutputStream.write(bytes);
    }
    fileInputStream.close();
    fileOutputStream.close();
}
```

