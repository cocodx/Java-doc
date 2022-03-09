**正文**

今天探究BeanDefinition里面的每个属性值都是干什么用的

#### 1.1 parentName
设置这个BeanDefinition的父BeanDefinition的名称。没怎么用过

#### 1.2 beanClassName
设置这个bean的类名称，在工厂后置处理器处理的时候，原有的beanClassName可以被替换

#### 1.3 beanName
BeanDefinition是没有这个属性的，但是BeanDefinition是存到一个BeanFactory的实现，DefaultListableBeanFactory里面的一个map容器里面。BeanName就是key。现在用注解来扫描bean，beanName一般就是驼峰写法，还有一种就是类路径+#0。
```java
    /** Map of bean definition objects, keyed by bean name. */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
```

#### 1.4 scope
默认是单例，还有prototype。prototype是在getBean的时候才回去生成对象，在容器启动的时候是不会去生成bean对象。而且之后spring也不会去管理这个bean对象，而是jvm去进行管理。

#### 1.5 lazyInit
是否懒加载，false的话，容器启动的时候会扫描BeanDefinition，再将BeanDefinition生成为Bean对象。

#### 1.6 DependsOn 数组
设置这个bean所依赖的beans，并且bean工厂会先去初始化这些依赖的bean

#### 1.7 autowiredCandidate 布尔
当这个为false的时候，OrderController里面Autowired的OrderService，当OrderService的autowiredCandidate设置为flase，是没资格被别人注入的。

#### 1.8 primary 布尔
当所依赖的bean类型，匹配到了多个，其中有一个primary设置为true，则注入进去，否则会报二义性错误，期待一个却发现多个

#### 1.9 factoryBeanName和factoryMethodName
当一个bean对象不是用反射生成的，那么可能是使用工厂生成的。

#### 2.0 ConstructorArgumentValues
这个是构造方法注入，在xml里面使用<Constructor-agrs>标签的时候，BeanDefinition这个属性有值。

#### 2.1 MutablePropertyValues
set方法注入，在xml扫描里面使用<property>标签的时候，BeanDefinition的这个属性里面会有值。