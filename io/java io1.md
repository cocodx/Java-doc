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
