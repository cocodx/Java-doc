#### 报错信息如下
```java
14:20:54.866 [qtp1796877471-156] ERROR c.yamcanda.commons.utils.ExcelUtils - 获取工作表对象失败
java.io.IOException: Stream Closed
	at java.base/java.io.FileInputStream.readBytes(FileInputStream.java)
	at java.base/java.io.FileInputStream.read(FileInputStream.java:279)
	at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:252)
	at java.base/java.io.BufferedInputStream.read1(BufferedInputStream.java:292)
	at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:351)
	at org.apache.poi.util.BoundedInputStream.read(BoundedInputStream.java:121)
	at org.apache.poi.util.IOUtils.copy(IOUtils.java:420)
	at org.apache.poi.util.IOUtils.copy(IOUtils.java:399)
	at org.apache.poi.util.IOUtils.peekFirstNBytes(IOUtils.java:79)
	at org.apache.poi.util.IOUtils.peekFirst8Bytes(IOUtils.java:66)
	at org.apache.poi.poifs.filesystem.FileMagic.valueOf(FileMagic.java:171)
	at org.apache.poi.openxml4j.opc.internal.ZipHelper.verifyZipHeader(ZipHelper.java:143)
	at org.apache.poi.openxml4j.opc.internal.ZipHelper.openZipStream(ZipHelper.java:175)
	at org.apache.poi.openxml4j.opc.ZipPackage.<init>(ZipPackage.java:104)
	at org.apache.poi.openxml4j.opc.OPCPackage.open(OPCPackage.java:301)
	at org.apache.poi.ooxml.util.PackageHelper.open(PackageHelper.java:37)
  ...
```

![image](https://user-images.githubusercontent.com/97614802/202106255-5fb3b7d1-5eb2-4960-89f1-154f7ad0de19.png)

在new这个HSSF对象报错之后，内部代码已经把
**new HSSFWorkbook(fileInputStream);** fileInputStream关闭了，再去new XSSF从fileInputStream流中读取信息的线程就会抛出异常：
java.io.IOException: Stream Closed
