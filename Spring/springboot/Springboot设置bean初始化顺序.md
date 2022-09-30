#### 背景介绍

Flyway管理数据库工具，新建schema，再去执行初始化表sql，初始化表数据sql

Flowable流程框架，会自动在schema下新建表。

**需要Flyway先初始化到spring，把schema建立好，再去执行Flowable初始化，这时候再把用户同步到flowable的用户表**

##### 1.首先在SpringBoot启动类下，禁止自动注入FlywayAutoConfiguration
```java
@SpringBootApplication(scanBasePackages = {"com.yamcanda.icube.*"}, exclude = {FlywayAutoConfiguration.class})
```

##### 2.在FlowableConfig使用@DependsOn注解，设置加载条件

```java
@Configuration
@DependsOn({"transactionManager", "flywayConfig"})
@ComponentScan(basePackages = {
        "org.flowable.ui.modeler",
        "org.flowable.ui.modeler.repository",
        "org.flowable.ui.modeler.service",
        "org.flowable.ui.common.tenant",
        "org.flowable.ui.common.repository"
        , "org.flowable.ui.task.service.debugger"
})
public class FlowableConfig {
```
