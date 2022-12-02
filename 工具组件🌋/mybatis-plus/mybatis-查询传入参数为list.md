#### mybatis传入参数为list查询

```java
<if test="query.riskStatusList!=null and query.riskStatusList.size()>0">
    AND pmp.risk_status in
    <foreach collection="query.riskStatusList" item="riskStatus" index="index" open="(" close=")"
             separator=",">
        #{riskStatus}
    </foreach>
</if>
```
