#### mybatis-plus分页

##### 1.开启分页插件
```java
@Configuration
public class MyBatisPlusConfig {
 
    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
```

##### 2.其他代码
```java
@Mapper
@Repository
public interface FlowableProcessDefinitionMapper extends BaseMapper<UserEntity> {

     IPage<ProcessDefinitionEntity> findPage(IPage<ProcessDefinitionEntity> page, @Param("query") ProcessDefinitionQuery query) ;
}

@Service
public class ProcessService extends ServiceImpl<UserMapper, UserEntity> {

    @Autowired
    private FlowableProcessDefinitionMapper flowableProcessDefinitionMapper;

    /**
     * 流程实例分页
     * @param query
     * @return
     */
    public IPage<ProcessDefinitionEntity> findPage(@Param("query") ProcessDefinitionQuery query){
        IPage<ProcessDefinitionEntity> page = new Page<>(query.getCurrent(), query.getSize());
        return flowableProcessDefinitionMapper.findPage(page,query);
    }

}

xml文件

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.yamcanda.icube.bpm.mapper.FlowableProcessDefinitionMapper">
    <select id="findPage" parameterType="com.yamcanda.icube.bpm.query.ProcessDefinitionQuery"
            resultType="com.yamcanda.icube.bpm.entity.ProcessDefinitionEntity">
      SELECT
        t.CATEGORY_ AS category,
        t.ID_ AS id,
        t.NAME_ AS NAME,
        t.KEY_ AS modelKey,
        t.RESOURCE_NAME_ AS resourceName,
        t.VERSION_ AS version,
        t.DEPLOYMENT_ID_ AS deploymentId,
        t.TENANT_ID_ AS tenantId
    FROM
        act_re_procdef t INNER JOIN
    (SELECT key_,MAX(version_) as version_ FROM act_re_procdef GROUP BY key_) b
    ON t.key_=b.key_ and t.version_ =b.version_
    <where>
        <if test="query.name!=null and query.name!=''">
           and t.NAME_ LIKE CONCAT('%',#{query.name}::text,'%')
        </if>
        <if test="query.modelKey!=null and query.modelKey!=''">
            or t.KEY_ LIKE CONCAT('%',#{query.modelKey}::text,'%')
        </if>
    </where>
    ORDER BY
        t.VERSION_ DESC
    </select>
</mapper>
```
