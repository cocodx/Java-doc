
```java
mysql_common.properties	
DEFAULT_GROUP
java1234.mysql.common=mysqlconfig
	
redis_common.properties  	
DEFAULT_GROUP
java1234.redis.common=redisconfig

crm业务项目	
crm.properties
CRM_GOURP
java1234.crm.config=crm

业务模块项目OA
oa.properties
OA_GOURP
java1234.oa.config=oa
```

要求在同一个namespace下面

对应项目中的配置，统一用数组格式
```java
spring.cloud.nacos.config.extension-configs[0].data-id=mysql_common.properties
spring.cloud.nacos.config.extension-configs[0].group=DEFAULT_GROUP
spring.cloud.nacos.config.extension-configs[0].refresh=true

spring.cloud.nacos.config.extension-configs[1].data-id=redis_common.properties
spring.cloud.nacos.config.extension-configs[1].group=DEFAULT_GROUP
spring.cloud.nacos.config.extension-configs[1].refresh=true


spring.cloud.nacos.config.extension-configs[2].data-id=crm.properties
spring.cloud.nacos.config.extension-configs[2].group=DEFAULT_GROUP
spring.cloud.nacos.config.extension-configs[2].refresh=true
```
