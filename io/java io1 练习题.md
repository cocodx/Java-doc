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
