# BeanDefinition是什么？  

## 1.1 BeanDefinition是什么  

bean特征：bean是一个对象，有名字，有class类型，有scope（单例、prototype），有role（属于应用的bean、还是spring框架的bean），有是否延迟初始化（lazy-init），有它依赖的其他bean，如果这个bean不好造（不能直接反射生成的话），可能还有个工厂方法和工厂bean呢。

BeanDefinition就是Bean的具体特征，这个就是bean的原材料，从代码上说，是最小化的根接口，规定一些必要属性的get和set方法。

```
public class SpringDefinition{
    //bean class 名
    String beanClassName;
    //工厂bean 名
    String factoryBeanName;
    //工厂方法 名
    String factoryMethodName;
    //singleton、prototype
    String scope;
    //是否延迟初始化
    boolean isLazyInit;
    //依赖的bean
    String[] dependsOn;
    //bean的角色，框架，应用
    int roles;
    //是否为主候选bean
    boolean primary;
    ...其他属性
}
```

bean definition描述了一个bean实例的各种属性，尤其声明了，这是一个最小化接口，主要目的是允许bean factory后置处理器，对bean property和其他元数据进行修改。而且，这是2004年的接口，贼核心的api。

## 1.2 BeanDefinition的接口实现有哪些？

![image](../images/1646758413(1).jpg)

这个接口有一个子接口，是AnnotatedBeanDefinition。这个接口定义如下：

```java
/**
 * Extended {@link org.springframework.beans.factory.config.BeanDefinition}
 * interface that exposes {@link org.springframework.core.type.AnnotationMetadata}
 * about its bean class - without requiring the class to be loaded yet.
 * 这个接口扩展了BeanDefinition，可以获得Bean Definition中的bean class上的注解元数据
 * 举个例子，假设我们用@Controller，标注了某个类，那这里就可以获取到@Controller这个注解里的信息
 *
 *
 * @author Juergen Hoeller
 * @since 2.5
 * @see AnnotatedGenericBeanDefinition
 * @see org.springframework.core.type.AnnotationMetadata
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {

    /**
        * Obtain the annotation metadata (as well as basic class metadata)
        * for this bean definition's bean class.
        * @return the annotation metadata object (never {@code null})
        */
    AnnotationMetadata getMetadata();

}
```

这个接口能取到bean definition中对应的bean class上标注的注解元数据。
比如使用下面的controller举例：

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
    @AliasFor(
        annotation = Component.class
    )
    String value() default "";
}
```

那这个AnnotatedBeanDefinition就能获取到service中的value字段的值。
下面有一个简单的例子
```java
@Qualifier("qualifierStudent")
@Service("studentServiceImpl")
public class StudentService {

    public void studentName(String name){
        System.out.println(name);
    }
}
```

```java
public static void main( String[] args )
    {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext
                ("com.springframework.learn");
        AnnotatedBeanDefinition studentServiceBeanDefinition = (AnnotatedBeanDefinition) configApplicationContext.getBeanDefinition("studentServiceImpl");

        AnnotationMetadata metadata = studentServiceBeanDefinition.getMetadata();
        Map<String,Object> resultQ = metadata.getAnnotationAttributes("org.springframework.stereotype.Service");
        System.out.println(resultQ.get("value"));
    }
```

我这边打印的信息就是：
> studentServiceImpl

## 1.3接口下的实现类

![image](../images/1646760508(1).jpg)

基本上**org.springframework.beans.factory.support.AbstractBeanDefinition**充当了基本的实现，基本上，该实现的方法都实现了，除了一个

```java
/**
        * Clone this bean definition.
        * To be implemented by concrete subclasses.
        * @return the cloned bean definition object
        */
    public abstract AbstractBeanDefinition cloneBeanDefinition();
```

再看看**org.springframework.beans.factory.support.GenericBeanDefinition**

```java
public class GenericBeanDefinition extends AbstractBeanDefinition {

    @Nullable
    private String parentName;


    /**
        * 这里类似于builder模式，先生成一个实例，再自己各种set方法和设置相关属性
        * Create a new GenericBeanDefinition, to be configured through its bean
        * properties and configuration methods.
        * @see #setBeanClass
        * @see #setScope
        * @see #setConstructorArgumentValues
        * @see #setPropertyValues
        */
    public GenericBeanDefinition() {
        super();
    }

    @Override
    public AbstractBeanDefinition cloneBeanDefinition() {
        return new GenericBeanDefinition(this);
    }
}
```

再看看**org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition**
```java
public class AnnotatedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;
    
    /**
        * Create a new AnnotatedGenericBeanDefinition for the given bean class.
        * @param beanClass the loaded bean class
        */
    public AnnotatedGenericBeanDefinition(Class<?> beanClass) {
        setBeanClass(beanClass);
        this.metadata = AnnotationMetadata.introspect(beanClass);
    }
    
    /**
        * Create a new AnnotatedGenericBeanDefinition for the given annotation metadata,
        * allowing for ASM-based processing and avoidance of early loading of the bean class.
        * Note that this constructor is functionally equivalent to
        * {@link org.springframework.context.annotation.ScannedGenericBeanDefinition
        * ScannedGenericBeanDefinition}, however the semantics of the latter indicate that a
        * bean was discovered specifically via component-scanning as opposed to other means.
        * @param metadata the annotation metadata for the bean class in question
        * @since 3.1.1
        */
    public AnnotatedGenericBeanDefinition(AnnotationMetadata metadata) {
        Assert.notNull(metadata, "AnnotationMetadata must not be null");
        if (metadata instanceof StandardAnnotationMetadata) {
            setBeanClass(((StandardAnnotationMetadata) metadata).getIntrospectedClass());
        }
        else {
            setBeanClassName(metadata.getClassName());
        }
        this.metadata = metadata;
    }
    
    
    @Override
    public final AnnotationMetadata getMetadata() {
        return this.metadata;
    }

}

```
很简单，就是多了获取bean class的注解的功能。
再看看**org.springframework.context.factory.annotation.ScannedGenericBeanDefinition**
```java
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;
    
    
    /**
        * Create a new ScannedGenericBeanDefinition for the class that the
        * given MetadataReader describes.
        * @param metadataReader the MetadataReader for the scanned target class
        */
    public ScannedGenericBeanDefinition(MetadataReader metadataReader) {
        Assert.notNull(metadataReader, "MetadataReader must not be null");
        this.metadata = metadataReader.getAnnotationMetadata();
        setBeanClassName(this.metadata.getClassName());
        setResource(metadataReader.getResource());
    }
    
    
    @Override
    public final AnnotationMetadata getMetadata() {
        return this.metadata;
    }
    
    @Override
    @Nullable
    public MethodMetadata getFactoryMethodMetadata() {
        return null;
    }

}

```
ScannedGenericBeanDefinition和AnnotatedBeanDefinition，代码是一样的啊，但是这个ScannedGenericBeanDefinition是在context包里面的，注解上说使用asm获取注解信息。这个和上面那个的区别就是：

> org.springframework.beans.factory.annotation.AnnotatedBeanDefinition 位于spring-beans，使用反射
> org.springframework.context.factory.annotation.ScannedGenericBeanDefinition 位于spring-context，使用asm

再看看**org.springframework.beans.factory.support.RootBeanDefinition**(位于spring-beans)，这个类下面有一个子类，位于spring-context的**org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader.ConfigurationClassBeanDefinition**,这两个类能看出来，应该和@Configuration注解有莫大关系。