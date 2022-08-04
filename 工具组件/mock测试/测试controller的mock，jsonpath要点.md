SpringbootTest默认集成junit5，依赖引入

SpringBootTest依赖引入

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

单独使用JsonPath的依赖引入，感觉可以替换Fastjson用了。表达式用去来很方便，比Fastjson构造对象去取，真就是一步到位。

```xml
<dependency>
    <groupId>com.jayway.jsonpath</groupId>
    <artifactId>json-path</artifactId>
    <version>2.7.0</version>
</dependency>
```

|OPerator|Description|
|-|-|
|$|表示的是根节点|
|@|遍历数组的时候，代表当前节点|
| * |所有*|
|..|深度搜索下面所有的元素|
|.<name>|子元素|
 
