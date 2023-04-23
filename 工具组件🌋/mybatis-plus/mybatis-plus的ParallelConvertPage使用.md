#### mybatis-plus的ParallelConvertPage使用

用于将分页查询的结果进行并行转换

使用ParallelConvertPage方法需要注意以下几点：

1、在mapper查询返回IPage类型，使用注解查询、LambdaQueryWrapper、xml等等都行
```java
@Select("select * from user")
IPage<User> getUserList(Page<User> page);
```

2，在查询方法中，需要调用Page对象的setOptimizeCountSql方法，将优化总记录数查询的开关打开。可以单独设置，也可以统一设置
```java
Page<User> page = new Page<>(1, 10);
page.setOptimizeCountSql(true);
IPage<User> userPage = userMapper.getUserList(page);
```

3，在获取分页查询结果时，调用ParallelConvertPage方法进行并行转换。例如：
```java
List<UserDTO> userList = userPage.getRecords().parallelStream().map(user -> {
    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(user, userDTO);
    return userDTO;
}).collect(Collectors.toList());
```

通过以上操作，就可以在Mybatis-Plus中使用ParallelConvertPage方法进行分页查询结果的并行转换了。同时需要注意，由于并行转换需要占用系统资源，可能会对系统的性能造成影响，因此需要根据具体情况进行评估和调整。
