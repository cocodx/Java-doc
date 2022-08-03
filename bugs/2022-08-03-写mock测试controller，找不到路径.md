
@WebMvcTest 注解的controllers是要测试的controller类，而不是本类test。导致的

```java
@WebMvcTest(properties = {"spring.profiles.active=test"}, controllers = {ScrapRecordController.class})
```
