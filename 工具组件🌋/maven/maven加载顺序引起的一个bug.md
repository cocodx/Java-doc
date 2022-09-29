#### javafaker的依赖，在pom中靠上引发的依赖冲突

```java
....上面一大堆依赖

<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>${javafaker.version}</version>
    <scope>test</scope>
</dependency>
```

用maven-helper查看

![image](https://user-images.githubusercontent.com/97614802/193037123-11220916-6604-463a-8dc8-df7ac7b643ed.png)


重复的类加载。

javafaker里面依赖了snakeyml文件，把这个移到下面来。
（由上靠下，靠上的优先加载）

那现在javafaker的依赖，挪到了最后面，就不优先加载了，优先加载1.2.9的，就没有报加载yml文件失败的问题。
