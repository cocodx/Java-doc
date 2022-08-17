是由于redisTemplate调用increment是要再没有事务的方法里才行执行
```java
redisTemplate.opsForValue().increment(key, delta);
```

所以这里不能使用事务
![image](https://user-images.githubusercontent.com/97614802/185083589-03f230cc-819d-48d8-bb0c-3d94006d3522.png)
