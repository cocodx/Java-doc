往Spring容器中注入Bean的注解有如下：

* @Bean
* @Controller，@Service，@Repository，@Component 
* @Import

##### @Import

这是这个student类，我们要做的就是把他导入到spring容器中来。

```java
@Data
public class Student {

    private Long id;
    private String userName;
    private Long classId;
    private Long age;
    private String remark;
}
```

这是导入配置类

```java
@Configuration
@Import(Student.class)
public class ImportConfig {
}
```

测试一下看看

```java
@Test
public void test1(){
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);
    DefaultListableBeanFactory defaultListableBeanFactory = annotationConfigApplicationContext.getDefaultListableBeanFactory();
    Student bean = defaultListableBeanFactory.getBean(Student.class);
    System.out.println(bean);
}
```

![image](https://user-images.githubusercontent.com/97614802/183328578-796af47d-a6b4-412d-9539-7c6191bf5a11.png)
