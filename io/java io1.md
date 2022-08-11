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
