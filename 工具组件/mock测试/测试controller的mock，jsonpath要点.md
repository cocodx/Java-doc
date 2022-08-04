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

文档地址：https://github.com/json-path/JsonPath

|OPerator|Description|
|-|-|
|$|表示的是根节点|
|@|遍历数组的时候，代表当前节点|
| * |所有*|
|..|深度搜索下面所有的元素|
|.name |子元素|
|[start:end] |遍历数组|
|[?(expression)]|过滤表达式，表达式的值必须是一个boolean类型|

```json
{
    "store": {
        "book": [
            {
                "category": "reference",
                "author": "Nigel Rees",
                "title": "Sayings of the Century",
                "price": 8.95
            },
            {
                "category": "fiction",
                "author": "Evelyn Waugh",
                "title": "Sword of Honour",
                "price": 12.99
            },
            {
                "category": "fiction",
                "author": "Herman Melville",
                "title": "Moby Dick",
                "isbn": "0-553-21311-3",
                "price": 8.99
            },
            {
                "category": "fiction",
                "author": "J. R. R. Tolkien",
                "title": "The Lord of the Rings",
                "isbn": "0-395-19395-8",
                "price": 22.99
            }
        ],
        "bicycle": {
            "color": "red",
            "price": 19.95
        }
    },
    "expensive": 10
}
```

|jsonPath|Result|
|-|-|
| $.store.* |获取store元素底下所有的元素|
| $.store.book[* ].author  | 获取book下所有的author的值|
| $..author | 获取所有的author的值 |
| $.store..price | 获取所有price的值 | 

 
