#### 具体如下

某个字段xxxxType,当为0，1，2，3时，要分别去校验B、C、D不能为空的写法。使用JSR303校验

使用原理：使用不同的接口，绑定不同的字段，在Controller层判断xxxxType的值，切到对应的接口去进行校验

##### 接收参数对象定义
```java
@NotEmpty(message="type不为空！")
private String type;

@NotEmpty(groups={BindUserIds.class},message="userIds不能为空！")
private String userIds;

@NotEmpty(groups={BindDeptIds.class},message="deptIds不能为空！")
private String dpetIds;

@NotEmpty(groups={BindRoleIds.class},message="roleIds不能为空！")
private String roleIds;

@NotEmpty(groups={BindRelatePostion.class},message="relationPostionIds不能为空！")
private String relationPostionIds;
```

##### 定义不同的接口
BindUserIds,BindDeptIds,BindRoleIds,BindRelatePostion 都是借口

##### Controller层使用

第一种是直接在接收对象那里加上接口名称
```java
public void xxxxController(@RequestBody @BindUserIds @Valid XXXXObject){
}
```

第二种是根据接收的值，进行判断切换接口，进行校验
```java
public void xxxxController(@RequestBody @Valid XXXXObject object){
  Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  Set<ConstraintViolation<XXXXObject>> result = null;
  if("0".equals(object.getType())){
    result = validator.validate(object, BindUserIds.class);
  }else if("1".equals(object.getType())){
    result = validator.validate(object, BindDeptIds.class);
  }else if("2".equals(object.getType())){
    result = validator.validate(object, BindRoleIds.class);
  }else if("3".equals(object.getType())){
    result = validator.validate(object, BindRelatePostion.class);
  }
  //对结果进行轮询，再抛出异常返回
  if (result != null && !result.isEmpty()) {
      StringBuilder sb = new StringBuilder();
      for (ConstraintViolation<AddSignTaskDto> violation : result) {
          sb.append(violation.getMessage());
      }
      throw new XXXXXException(500, sb.toString());
  }
}
```

