##### Springboot项目集成jetty

1. pom.xml文件依赖引入
2. yml配置


```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <!--去掉tomcat容器-->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!--添加jetty容器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

```yml
server:
  port: 8888
  servlet:
    context-path: /jetty

  jetty:
    threads:
      acceptors: 20
    max-http-form-post-size: 100000000
```

