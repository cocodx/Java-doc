mock创建出一个虚假的对象，模拟出一个对象，代替真实的对象，以达到我们可以
* 验证改对象的某些方法的调用情况，调用了多少次，参数是多少
* 对对象的行为进行定义，来指定返回的结果

#### Mock对象
mock方法来自 org.mockito.Mock,它表示可以mock一个对象或者是接口。

**当使用mock对象，如果不对其行为进行定义，则mock对象方法的返回值为返回类型的默认值**

疑问：
```java
这是啥意思？
Mockito.mock(Book.class);
Mockito.verify
//打桩，当它执行啥返回，永远返回100
Mockito.when().thenReturn(100);
```


##### 验证与断言
断言用的类是Assertions
```java
//等于100，判断相等
Assertions.assertEquals(100,random.nextInt());
```

##### @mock注解
快速创建Mock对象。
mock注解，要跟MockitoAnnotation.openMocks方法()生效一起使用

##### @BeforeEach与@AfterEach
在执行测试方法之前，先执行BeforeEach

AfterEach，在测试执行方法之后，去执行这个方法

##### spy方法与spy注解
被spy的对象会走真实的方法，而mock对象不会

spy方法的参数是对象实例，mock的参数是class

