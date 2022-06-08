
消费者依赖引入feign
```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

新建feign的service
```java
@FeignClient("ribbon-produce")
public interface StockFeignService {

    @RequestMapping("/produce/commonconfig1")
    public String test(@RequestParam("info") String info);
}
```

```java
#配置文件 注册地址拉取
spring:
  application:
    name: ribbon-application
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
```

在启动类打上注解
```java
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ribbon.use.feign")
```

访问http地址
http://localhost:7474/testFeign?str=yyyyyyyyyyyyyyy