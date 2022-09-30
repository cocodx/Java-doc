#### MultipartFile参数

MultipartFile.getName() MultipartFile.getOriginalName() 区别

一个name是传参的name，一个文件名称

![image](https://user-images.githubusercontent.com/97614802/193220791-41f27425-ce06-46a5-963e-4a7b36fb5718.png)

##### 判断文件名是否含有特殊字符
```java
public static Boolean hasErrorCharacter(String fileName){
  String regEx =  ".*[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";
  Pattern p = Pattern.compile(regEx);
  Matcher matcher = p.matcher(fileName);
  return matcher.matches();
}
```
