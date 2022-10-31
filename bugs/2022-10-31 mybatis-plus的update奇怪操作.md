![image](https://user-images.githubusercontent.com/97614802/198964574-984019db-8b83-4a1b-b2c0-f4075a59d85b.png)

设置了content_id_两次，很奇怪，不知道哪里出问题了。

![image](https://user-images.githubusercontent.com/97614802/198964798-babef294-ef43-447f-bf0f-54ad6adeb638.png)

但是我这就设置了一次啊

找到原因了，mybatisplus的update方法用错了

```java
/**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity        实体对象 (set 条件值,可以为 null)
     * @param updateWrapper 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
```

entity 是set值，wrapper是组装where条件的

修改之后，确实就正常了

![image](https://user-images.githubusercontent.com/97614802/198967293-fd21e5cd-6441-47c3-9c4b-cfc56f63e787.png)
