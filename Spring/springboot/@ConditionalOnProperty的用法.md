#### @ConditionalOnProperty 使配置类生效

当system的property中test-annotation1，value为true的时候，才会往Spring容器中注入SysUser

```java
@ConditionalOnProperty(name = "test-annotation1" , havingValue = "true")
@Configuration
public class TestAnnotation1 {

    @Bean
    public SysUser testPrintSomething(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("大军");
        sysUser.setPassword("password");
        System.out.println("1234:"+sysUser.toString());
        return sysUser;
    }
}
```

![image](https://user-images.githubusercontent.com/97614802/183326124-24aa7e14-e2a3-4c41-862d-0a31dd121dcc.png)

Springboot项目，启动运行，已打印

![image](https://user-images.githubusercontent.com/97614802/183326214-d427d546-887f-41e2-b22c-524641bb9a78.png)
