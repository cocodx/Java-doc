> 正文：手动注入的大致思路

1. 选择一个BeanFactory
2. 获取到Bean对象的BeanName
3. 把BeanDefinition注入到BeanFactory里面去

#### 1.1 选择DefaultListableBeanFactory
#### 1.2 muTablePropertyValues这种方式注入

```java
@Data
public class StudentControllerProperties {

    private StudentService studentService;
    private String name;
    private Integer age;
    
}
```
```java
public interface StudentService {

    void insert(String name);
    void update(String name);
    void delete(String name);
    void get(String name);
}

```
```java
public class StudentServiceImpl implements StudentService {
    @Override
    public void insert(String name) {
        System.out.println(String.format("插入name：%s，的数据",name));
    }

    @Override
    public void update(String name) {
        System.out.println(String.format("更新name：%s，的数据",name));
    }

    @Override
    public void delete(String name) {
        System.out.println(String.format("删除name：%s，的数据",name));
    }

    @Override
    public void get(String name) {
        System.out.println(String.format("查询name：%s，的数据",name));
    }
}
```
```java
public class RegisterBeanDefinitionAgain {

    public static void main(String[] args) {
        //1、选择一个容器BeanFactory的实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2、获取BeanName
        //2.1、注入StudentServiceImpl的BeanDefinition
        GenericBeanDefinition genericBeanDefinition1 = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(StudentServiceImpl.class).getBeanDefinition();
        String serviceName = AnnotationBeanNameGenerator.INSTANCE.generateBeanName(genericBeanDefinition1,beanFactory);
        System.out.println("驼峰serviceName："+serviceName);
        beanFactory.registerBeanDefinition(serviceName,genericBeanDefinition1);
        //2.2、构建StudentControllerProperties的BeanDefinition，同时property方式set注入studentService的BeanDefinition依赖
        GenericBeanDefinition genericBeanDefinition2 = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(StudentControllerProperties.class)
                .addPropertyValue("name","property values")
                .addPropertyValue("age","100")
                .addPropertyReference("studentService",serviceName)
                .getBeanDefinition();
        String beanName = AnnotationBeanNameGenerator.INSTANCE.generateBeanName(genericBeanDefinition2,beanFactory);
        System.out.println("驼峰beanName："+beanName);
        String beanName1 = DefaultBeanNameGenerator.INSTANCE.generateBeanName(genericBeanDefinition2,beanFactory);
        System.out.println("#0beanName:"+beanName1);
        beanFactory.registerBeanDefinition(beanName,genericBeanDefinition2);

        StudentService studentService = (StudentService) beanFactory.getBean(serviceName);
        StudentControllerProperties studentControllerProperties = (StudentControllerProperties) beanFactory.getBean(beanName);

        Assert.isTrue(studentService==studentControllerProperties.getStudentService(),"卧槽，这还不是相等的吗！");
        System.out.println(studentService.hashCode());
        System.out.println(studentControllerProperties.getStudentService().hashCode());
    }
}
```
###### 最后结果如图所示
![image](../images/1646842589(1).jpg)

#### 1.3 constructorArgumentValues这种方式注入
```java
public class StudentControllerConstructor {

    private StudentService studentService;
    private String name;
    private Integer age;

    public StudentControllerConstructor(StudentService studentService, String name, Integer age) {
        this.studentService = studentService;
        this.name = name;
        this.age = age;
    }

    public StudentService getStudentService() {
        return studentService;
    }
}
```
```java
public class RegisterBeanDefinitionAgain {

    public static void main(String[] args) {
        //1、选择一个容器BeanFactory的实现
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2、获取BeanName
        //2.1、注入StudentServiceImpl的BeanDefinition
        GenericBeanDefinition genericBeanDefinition1 = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(StudentServiceImpl.class).getBeanDefinition();
        String serviceName = AnnotationBeanNameGenerator.INSTANCE.generateBeanName(genericBeanDefinition1,beanFactory);
        System.out.println("驼峰serviceName："+serviceName);
        beanFactory.registerBeanDefinition(serviceName,genericBeanDefinition1);
        //2.2、构建StudentControllerProperties的BeanDefinition，同时property方式set注入studentService的BeanDefinition依赖
        GenericBeanDefinition genericBeanDefinition2 = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(StudentControllerProperties.class)
                .addPropertyValue("name","property values")
                .addPropertyValue("age","100")
                .addPropertyReference("studentService",serviceName)
                .getBeanDefinition();
        String beanName = AnnotationBeanNameGenerator.INSTANCE.generateBeanName(genericBeanDefinition2,beanFactory);
        System.out.println("驼峰beanName："+beanName);
        String beanName1 = DefaultBeanNameGenerator.INSTANCE.generateBeanName(genericBeanDefinition2,beanFactory);
        System.out.println("#0beanName:"+beanName1);
        beanFactory.registerBeanDefinition(beanName,genericBeanDefinition2);

        StudentService studentService = (StudentService) beanFactory.getBean(serviceName);
        StudentControllerProperties studentControllerProperties = (StudentControllerProperties) beanFactory.getBean(beanName);

        Assert.isTrue(studentService==studentControllerProperties.getStudentService(),"卧槽，这还不是相等的吗！");
        System.out.println(studentService.hashCode());
        System.out.println(studentControllerProperties.getStudentService().hashCode());

        //3.1 constructorArgumentValues注入
        GenericBeanDefinition genericBeanDefinition3 = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(StudentControllerConstructor.class)
                .addConstructorArgReference(serviceName)
                .addConstructorArgValue("constructor")
                .addConstructorArgValue("222")
                .getBeanDefinition();
        String beanName3 = DefaultBeanNameGenerator.INSTANCE.generateBeanName(genericBeanDefinition3,beanFactory);
        System.out.println("beanName3："+beanName3);
        beanFactory.registerBeanDefinition(beanName3,genericBeanDefinition3);
        StudentControllerConstructor controllerConstructor = (StudentControllerConstructor) beanFactory.getBean(beanName3);
        System.out.println("构造器注入，service的hashcode："+controllerConstructor.getStudentService().hashCode());
    }
}
```
###### 效果如图所示
![image](../images/1646843009(1).jpg)

property和constructor的区别在于：
```java
.addPropertyValue("name","property values")
.addPropertyValue("age","100")
.addPropertyReference("studentService",serviceName)
.getBeanDefinition();
```
```java
.addConstructorArgReference(serviceName)
.addConstructorArgValue("constructor")
.addConstructorArgValue("222")
.getBeanDefinition();
```