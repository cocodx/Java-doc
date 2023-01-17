#### mapper的方法
```java
page(page, lqw);
```

![image](https://user-images.githubusercontent.com/97614802/212796830-48cc0e81-99ea-4d5b-9b4b-2e62866d47a1.png)

IPage中有一个convert方法，把Do填充到Vo中

```java
page.convert(u->{
  UserVo v = new UserVo();
  BeanUtils.copyProperties(u,v);
  return v;
})
```
