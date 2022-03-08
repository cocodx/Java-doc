
# 1.1 Spring框架整体介绍

## 1.1.1 Core Container:Beans,Core,Context,SpEL  

![Image](../images/1646534416(1).jpg)
模块作用  
1）core  
主要包含Spring框架基本的核心工具类，Spring的其他组件都要用到这个包的类，Core模块是其他组件的基本核心  
2）Beans（BeanFactory的作用）  
它包含访问配置文件，创建和管理bean以及进行Inversion of controller I Dependency Injection（IOC/DI）相关操作的所有类  
3）Context（处理BeanFactory，ApplicationContext的作用）  
模块构建于Core和Beans模块基础之上，提供了一种类似与JNDI注册器的框架式的对象访问方法。Context模块继承了Beans的特性，为Spring核心提供了大量扩展，添加了国际化（例如资源绑定），事件传播，资源加载和对Context的透明创建的支持。Context模块同时也支持J2EE的一些特性，ApplicationContext接口是Context模块的关键。
本质区别：使用BeanFactory的bean是延时加载的，ApplicationContext是非延时加载的  

![Image](../images/1646535354(1).jpg)  

### 2.1研究问题

#### 2.1.1 研究BeanFactory和ApplicationContext的区别？  

BeanFactory是Spring容器的根接口，提供了最简单的容器功能，getBean，isSingleton等方法，位于spring-beans包，**启动的时候，不会去实例化bean，只有从容器中拿Bean的时候才会去实例化。**  

ApplicationContext是继承于BeanFactory，位于spring-context包，**启动的时候就把所有的Bean全部给实例化了。它还可以为Bean配置lazy-init=true来让Bean延迟实例化.**

#### 2.1.2 研究BeanFactory和FactoryBean的区别？  

#### 2.1.3 Spring通过xml注入和通过注解Configuration注入两种实现方式  
  
3、Configuration注入的几种方式  
new ApplicationContext的时候，带上注解类，类上面声明@ComponentScan，扫描包路径。
new ApplicationContext的时候，带上注解类，类里面有bean注解的方法。  
@ComponentScan的Filter使用  
excludeFilters：排除一些属性的扫描
includeFilters：包含  
实现自定义TypeFilter  
useDefaultFilters

Lazy注解和Scope注解  
@Conditionnal注解  

#### 往IOC容器添加组件的方式  

@bean  

@ComponentScan +@Controller，@Service，@Component，@Repository  

@Import（ImportSelector的使用，ImportBeanDefinitionRegister）

@ImportBeanDefinitionRegister  

通过FactoryBean接口注入  

#### 5.1 Bean的生命周期？  

bean的创建-->初始化-->destory  
BeanPostProcessor接口的使用，在bean初始化前后进行调用。  

##### 5.1.1 指定初始化和销毁方法@Bean(initMethod="",destoryMethod="")  

针对单实例bean的话，容器启动的时候，bean的对象就创建了，而且容器销毁的时候，也会调用Bean的销毁方法。  
针对多实例bean的话，容器启动的时候，bean是不会创建的而是在获取的时候创建，销毁是不受IOC容器管理的。  

##### 5.1.2 通过@PostConstruct和@ProDestory标注的方法，JSR250规范
