**正文**

今天探究BeanDefinition里面的每个属性值都是干什么用的

#### 1.1 parentName
设置这个BeanDefinition的父BeanDefinition的名称。没怎么用过

代码如下，用xml扫描beanDefinition的方式来设置parentName，发现子BeanDefinition可以重写和继承父BeanDefinition一些属性，属性，方法重写，构造器等
但是有一些属性不会被继承，例如：
* scope
* dependsOn[]
* singleton
* lazy init
代码如下
```java
@Data
@ToString
public abstract class AbstractTestBean {

    private String name;
    private int age;

}
```

```java
@Data
@ToString
public class TestBean extends AbstractTestBean {

}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean name="abstractTestBean" class="com.springframework.learn.parentname.AbstractTestBean" abstract="true">
        <property name="name" value="parentName"/>
        <property name="age" value="100"/>
    </bean>

    <!-- 设置testBean的parentName为abstractTestBean -->
    <bean name="testBean" class="com.springframework.learn.parentname.TestBean" parent="abstractTestBean">
        <property name="name" value="override"/>
    </bean>

</beans>
```

```java
public class UsingParentNameTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:parentName-beanDefinition.xml");
        TestBean testBean = (TestBean) context.getBean("testBean");
        System.out.println(testBean);
    }
}
```

##### 如下图所示，name被覆盖，age的值被继承了
![image](../images/1646838360(1).jpg)

配置了这个，子BeanDefinition就可以继承父BeanDefinition的一些属性值

#### 1.2 beanClassName
设置这个bean的类名称，在工厂的后置处理器处理时候，原有的beanClassName可以被替换

#### 1.3 beanName
BeanDefinition是没有这个属性的，但是BeanDefinition是存到一个BeanFactory的实现，
**org.springframework.beans.factory.support.DefaultListableBeanFactory**
DefaultListableBeanFactory里面的一个map容器里面。BeanName就是key。现在用注解来扫描bean，beanName一般就是驼峰写法，还有一种就是类路径+#0。
```java
    /** Map of bean definition objects, keyed by bean name. */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
```

#### 1.4 scope
默认是单例，还有prototype。prototype是在getBean的时候才回去生成对象，在容器启动的时候是不会去生成bean对象。而且之后spring也不会去管理这个bean对象，而是jvm去进行管理。

#### 1.5 lazyInit
是否懒加载，true的话，容器启动的时候会扫描BeanDefinition，再将BeanDefinition生成为Bean对象。推荐使用false，启动时候加载，问题及时发现，不然会在运行时报RuntimeException。

#### 1.6 DependsOn 数组
设置这个bean所依赖的beans，并且bean工厂会先去初始化这些依赖的bean

#### 1.7 autowiredCandidate 布尔
当这个为false的时候，OrderController里面Autowired的OrderService，当OrderService的autowiredCandidate设置为flase，是没资格被别人注入的。

#### 1.8 primary 布尔
当所依赖的bean类型，匹配到了多个，其中有一个primary设置为true，则注入进去，否则会报二义性错误，期待一个却发现多个

#### 1.9 factoryBeanName和factoryMethodName
当一个bean对象不是用反射生成的，那么可能是使用工厂生成的。

#### 2.0 ConstructorArgumentValues
这个是构造方法注入，在xml里面使用**Constructor-agrs**标签的时候，BeanDefinition这个属性有值。

```java
@Data
@ToString
public abstract class AbstractTestBean {

    private String name;
    private int age;

    public AbstractTestBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public AbstractTestBean() {
    }
}
```
```java
@Data
@ToString
public class TestBean extends AbstractTestBean {

    public TestBean(String name,int age) {
        super(name,age);
    }

    public TestBean() {
    }
}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean name="testBean" class="com.springframework.learn.parentname.TestBean">
        <constructor-arg index="0" value="得得得得得得得得"/>
        <constructor-arg index="1" value="11111111"/>
    </bean>

</beans>
```
```java
public class AnnotatedBeanDefinitionTest
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext
                ("com.springframework.learn");
        AnnotatedBeanDefinition studentServiceBeanDefinition = (AnnotatedBeanDefinition) configApplicationContext.getBeanDefinition("studentServiceImpl");

        AnnotationMetadata metadata = studentServiceBeanDefinition.getMetadata();
        Map<String,Object> resultQ = metadata.getAnnotationAttributes("org.springframework.stereotype.Service");
        System.out.println(resultQ.get("value"));
    }
}
```
###### 如下图所示，ConstructorArgumentValues有值
![image](../images/1646839121(1).jpg)

#### 2.1 MutablePropertyValues
set方法注入，在xml扫描里面使用**property**标签的时候，BeanDefinition的这个属性里面会有值。

```java
@Data
@ToString
public class TestBeanOne {

    private String name;
    private int age;
}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean name="testBean" class="com.springframework.learn.propertyValue.TestBeanOne">
        <property name="name" value="咚咚咚咚咚咚咚,蹦蹦蹦蹦蹦蹦"/>
        <property name="age" value="22222222"/>
    </bean>

</beans>
```
```java
public class UsingMuTablePropertyValues {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:muTablePropertyValues-beanDefinition.xml");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanFactory.getBeanDefinition("testBean");
        System.out.println(beanDefinition);

    }
}
```
###### 如下图所示，mutablepropertyvalues有值
![image](../images/1646839647(1).jpg)