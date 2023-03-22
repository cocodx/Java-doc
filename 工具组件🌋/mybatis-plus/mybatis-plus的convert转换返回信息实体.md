1.查出来的信息不足，需要再次进行封装（返回显示数据：例如，字典，enum等）

可以使用IPage提供的convert方法，进行实体转换。

```java
IPage<ReReceiptVo> voIPage = page.convert(entity -> {
  ReReceiptVo vo = new ReReceiptVo();
  BeanUtils.copyProperties(entity, vo);
  return vo;
});
```
