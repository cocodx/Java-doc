https://tech.meituan.com/2018/01/19/mybatis-cache.html

#### 一级缓存
在系统运行中，我们有可能在一次数据库会话中，执行多次查询条件完全相同的sql，mybatis提供了一级缓存的方案优化这部分场景，如果是相同的sql语句，会优先命中一级缓存，避免直接对数据进行查询，提高性能。

每个Sqlsession中持有了Executor，每个Executor中有一个LocalCache。当用户发起查询时，Mybatis根据当前执行的语句生成MappedStatement，在Local Cache进行查询，如果缓存命中的话，直接返回结果给用户，如果缓存没有命中的话，查询数据库，结果写入Local Cache，最后返回结果给用户。


在配置文件中设置，开启
```java
<setting name="localCacheScope" value="SESSION"/>
```

可以看到第一次查了，后来都没查，取得都是LocalCache
![image](../images/Snipaste_2022-07-14_02-33-14.png)

##### 一级缓存失效1

在sqlSession查询之后，再去新增，再去查询，发现一级缓存失效

![image](../images/Snipaste_2022-07-16_12-50-41.png)

##### 一级缓存失效2

两个sqlsession，在sqlsession中查询数据，使一级缓存生效，在sqlsession中更新数据库，验证一级缓存只在数据库会话中内部共享。

![image](../images/Snipaste_2022-07-16_13-03-31.png)

##### 一级缓存工作流程时序图

##### 缓存命中

```mermaid
sequenceDiagram
    participant Client
    participant Sql session
    participant Executor
    participant Cache
    Client->>Sql session: select
    Sql session->>Executor: query
    Executor->>Cache: localCache.getObject(key)
    Cache-->>Executor: 返回查询结果
    Executor->>Executor: 判断查询结果是否为null，不为null继续往下走，不查数据库
    Executor-->>Sql session: 返回查询结果
    Sql session-->>Client: 返回Cache查到的值
```

##### 缓存未命中

```mermaid
sequenceDiagram
    participant Client
    participant Sql session
    participant Executor
    participant Cache
    participant DB
    Client->>Sql session: select
    Sql session->>Executor: query
    Executor->>Cache: localCache.getObject(key)
    Cache-->>Executor: 返回查询结果
    Executor->>Executor: 判断查询结果是否为null
    Executor->>DB: dispatch
    DB-->>Executor: return
    Executor-->>Sql session: 返回查询结果
    Sql session-->>Client: 返回Cache查到的值
```

#### 二级缓存

一级缓存中，最大的共享范围就是一个SqlSession内部，如果多个SqlSession之间需要共享缓存，则需要使用到二级缓存。开启二级缓存后，会使用CachingExecutor装饰Executor，进入一级缓存的查询流程前，先在CachingExecutor进行二级缓存的查询。

![image](../images/Snipaste_2022-07-18_21-32-41.png)

##### 二级缓存配置
在mybatis配置文件中开启二级缓存
```xml
<setting name="cacheEnabled" value="true">
```
1.在Mybatis的映射XML中配置cache或者cache-ref
cache标签，用于声明这个namespace用于二级缓存，并且可以自定义配置
```xml
<cache/>
```
注意点：二级缓存是需要对应的实体实现序列化 Serializable

二级缓存不适用于映射文件存在多表查询的情况。
多表查询语句所在的namespace无法感知到其他namespace中的语句对多表查询中涉及的表进行的修改，引发脏数据问题。

解决办法：可以使用cache ref，让ClassMapper引用StudentMapper命名空间，这样两个映射文件操作的SQL操作都使用的是同一个缓存了。
