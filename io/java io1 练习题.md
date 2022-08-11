##### 遍历一个文件夹下所有文件
```java
/**
 * 循环打印，文件夹里的文件
 * D:\BaiduNetdiskDownload
 */
@Test
public void printFileNames(){
    File file = new File("D:\\BaiduNetdiskDownload");
    printPackageFiles(file);
}

private void printPackageFiles(File file){
    File[] files = file.listFiles();
    if (files.length>0 && file!=null){
        for (File file1:files){
            if (file1.isDirectory()){
                printPackageFiles(file1);
            }else{
                System.out.println(file1);
            }
        }
    }
}
```

##### 文件搜索
搜索D:aaa\ 目录中 .java 结尾的文件
```java
@Test
public void searchFiles(){
    File file = new File("D:\\BaiduNetdiskDownload");
    searchJavaFiles(file);
}

private void searchJavaFiles(File file) {
    File[] files = file.listFiles();
    if (files.length>0 && file!=null){
        for (File file1:files){
            if (file1.isDirectory()){
                searchJavaFiles(file1);
            }else{
                String name = file1.getName();
                String[] split = name.split("\\.");
                if (split.length == 2){
                    if (split[1].equals("java")){
                        System.out.println(file1);
                    }
                }
            }
        }
    }
}
```
