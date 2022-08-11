#### File类
File类操作电脑中文件，文件夹

java把电脑中文件、文件夹封装成了一个File类，对【文件、文件夹】进行操作，可以使用file类方法创建一个【文件，文件夹】，删除文件，文件夹，获取【文件，文件夹】，判断【文件，文件夹】，是否存在，遍历，获取文件大小，File类是一个与系统无关的类。

任务操作系统都可以使用类中的方法，File， directory，path

```java
File file = new File("d:/小于100KB.png");
//分号，路径分隔符; windows系统； linux:
System.out.println(File.pathSeparator);
//文件名称分隔符 windows\ linxu/
System.out.println(File.separator);
```

##### 路径-相对路径-绝对路径

相对路径：简化的路径，相对于当前项目的根目录,路径简化书写。

路径不区分大小写，windows使用反斜杠，转义字符，两个反斜杠，代表一个普通的字符

```java
File file1 = new File("resources\\shiro.ini");
System.out.println(file1.getAbsolutePath());
System.out.println(file1.getPath());
```
打印结果如下
> D:\code\ideaProjects\springboot-postgresql\resources\shiro.ini 
> 
> resources\shiro.ini

##### File类构造方法

pathName可以是绝对路径，也可以是相对路径

路径可以是文件结尾，也可以是文件夹结尾

路径可是存在，路径也可以是不存在

创建File对象，只是把字符串路径封装为file对象，不考虑路径的真假情况


```java
File file = new File(String pathName);
```

父路径和子路径可以单独书写，非常灵活，都可以变化

```java
File file2 = new File("d:", "a.txt");
File file3 = new File("d:", "b.txt");
System.out.println(file2);
System.out.println(file3);
```
打印如下
> d:a.txt
> d:b.txt

父路径和子路径可以单独书写，非常灵活，都可以变化
使用File作为父对象，非常灵活，可以使用File的方法对路径进行一些操作，再使用路径操作对象

```java
new File(File parent, String child)
```

##### 获取功能的方法

![image](https://user-images.githubusercontent.com/97614802/184074992-e9f9f631-9c87-4f65-919d-e198c8d45957.png)

```java
//获取file的绝对路径,无论构造方法的路径是绝对的，还是相对的
System.out.println(file.getAbsolutePath());;
//获取构造方法中，传入的路径
file.getPath();
//获取构造方法，传入路径的结尾部分，要么是文件，要么是文件夹
file.getName();
//获取的是，构建方法指向的文件的大小，文件夹是没有大小的概念的。不能获取文件夹大小length为0
file.length();
```

##### 判断功能的方法

```java
//判断功能的方法
file.exists();
//判断文件夹的方法
file.isDirectory();
//判断文件的方法
file.isFile();
```

##### File类创建删除功能的方法
```java
//当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。创建文件的路径，必须存在
public boolean createNewFile()
//删除由file表示的文件或目录
public boolean delete()
//创建由file表示的目录
public boolean mkdir()
//创建由file表示的目录，还有父目录
public boolean mkdirs()
```

##### File类遍历
```java
//遍历构造方法中给出的目录，会抛出空指针异常，【如果是文件，如果目录不存在】
public String[] list();
public File[] listFiles();
```
