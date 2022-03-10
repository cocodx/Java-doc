## fastjson

#### maven依赖引入
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.74</version>
</dependency>
```
json序列化过滤掉不用的属性
```java
SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
filter.getExcludes().add("beanClass");
log.info("testController:{}", JSON.toJSONString(beanDefinition,filter));
```
