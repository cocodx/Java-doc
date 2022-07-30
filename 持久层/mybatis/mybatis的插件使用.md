StatementHandler拦截获取sql

#### 普通情况下
要在配置文件中配置plugin标签
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 别名 -->
    <typeAliases>
        <package name="com.lg.safeapi.entity"/>
    </typeAliases>

    <plugins>
        <plugin interceptor="com.lg.safeapi.plugins.SqlPlugin"/>
    </plugins>
</configuration>
```

```java
@Intercepts({@Signature(type = StatementHandler.class,method = "parameterize",args={Statement.class})})
public class SqlPlugin implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(SqlPlugin.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //此处即org.apache.ibatis.executor.statement.StatementHandler.parameterize
        Object returnObject = invocation.proceed();
        Statement statement = (Statement) invocation.getArgs()[0];
        String finalExecutableSql = null;
        if (Proxy.isProxyClass(statement.getClass())){
            MetaObject metaObject = SystemMetaObject.forObject(statement);
            /*h的可能值 jdk代理 InvocationHandler
            * PreparedStatementLogger
            * StatementLogger
            * */
            Object h = metaObject.getValue("h");
            if(h instanceof StatementLogger){
                RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) invocation.getTarget();
                finalExecutableSql = routingStatementHandler.getBoundSql().getSql();
            }else{
                PreparedStatementLogger psl = (PreparedStatementLogger) h;
                finalExecutableSql = psl.getPreparedStatement().toString();
            }
        }else{
            finalExecutableSql = statement.toString();
        }
        log.info("最终不带占位符的可直接执行sql：{}",finalExecutableSql);
        return returnObject;
    }
}
```

#### 特殊情况下，自定义sqlSessionFactory，导致plugin不生效

直接在sqlsessionFactory里面去设置
```java
public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
    //由于自定义配置sqlSessionFactory导致plugin失效
    bean.setPlugins(new SqlPlugin());
    return bean.getObject();
}
```