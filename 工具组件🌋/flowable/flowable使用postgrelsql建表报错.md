#### 代码如下
```java
ProcessEngineConfiguration configuration;


configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres")
                .setJdbcUsername("postgres")
                .setJdbcPassword("password")
                .setJdbcDriver("org.postgresql.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                .setDatabaseSchema("flowable");
```
