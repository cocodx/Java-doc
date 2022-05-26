maven依赖
```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

因为spring cloud gateway是基于webflux的，如果非要web支持的话需要导入spring-boot-starter-webflux而不是spring-boot-start-web。

最简单的使用
```java
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: http://localhost:8965/
          predicates:
          - Path=/produce/get
```
访问路径 http://localhost:10010/produce/get
跳转到 http://localhost:8965/produce/get