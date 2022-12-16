#### if标签中判断值相等

mybatis中的映射方式是ongl方式

##### Integer

```xml
<if test="query.methodType!=null and query.methodType==0">
    LEFT JOIN bpm_proc_inst_ext bpie ON bpd.id = bpie.procinst_draft_id
</if>

<if test="query.methodType!=null and query.methodType==0">
    AND bpd.start_user_id::text = #{query.userCode}
    <if test="query.readFlag != null">
        AND bpie.read_flag = #{query.readFlag}
    </if>
    <if test="query.followFlag != null">
        AND bpie.follow_flag = #{query.followFlag}
    </if>
</if>
<if test="query.methodType!=null and query.methodType==1">
    <if test="query.hospId!=null">
        AND bpd.hosp_id = #{query.hospId}
    </if>
    <if test="query.corpCode!=null">
        AND bpd.corp_code = #{query.corpCode}
    </if>
</if>
```

##### String

```xml
<if test="companyId != null and companyId !='' ">  
	<if test="companyFlag == '1'.toString() ">   
		AND COMPANY_ID = #{companyId,jdbcType=VARCHAR}  
	</if>  
	<if test="companyFlag == '0'.toString() ">   
		AND COMPANY_ID != #{companyId,jdbcType=VARCHAR}  
	</if> 
</if>
```

