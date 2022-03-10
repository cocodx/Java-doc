> 从json文件中读取BeanDefinition

思路首先得到BeanDefinition的json文件格式，然后再将json文件转换为BeanDefinition，再注入到Bean容器里面去。

#### 1.1 打印BeanDefinition
由于BeanDefinition实现类AbstractBeanDefinition里面有个属性beanClass，是class类型的，转json会报错，所以再使用fastjson的时候，过滤一下这个属性

```java
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    public void sing(){
        testService.sing();
    }
}
```
```java
public class TestService {

    public void sing(){
        System.out.println("天都黑了，你都没来过，没来坐");
    }
}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean name="testController" class="com.springframework.learn.readjson.TestController">
        <constructor-arg ref="testService"/>
    </bean>

    <!-- 设置testBean的parentName为abstractTestBean -->
    <bean name="testService" class="com.springframework.learn.readjson.TestService">
    </bean>

</beans>
```
```java
@Slf4j
public class PrintlnBeanDefinitionJson {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:readJson.xml");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("testController");
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("beanClass");
        log.info("testController:{}", JSON.toJSONString(beanDefinition,filter));

        BeanDefinition beanDefinition1 = beanFactory.getBeanDefinition("testService");
        log.info("testService:{}", JSON.toJSONString(beanDefinition1,filter));
    }
}
```
#### 1.2 打印得到的json
```json
[
  {
    "abstract": false,
    "autowireCandidate": true,
    "autowireMode": 0,
    "beanClass": "com.springframework.learn.readjson.TestService",
    "beanClassName": "com.springframework.learn.readjson.TestService",
    "constructorArgumentValues": {
      "argumentCount": 0,
      "empty": true,
      "genericArgumentValues": [],
      "indexedArgumentValues": {}
    },
    "dependencyCheck": 0,
    "enforceDestroyMethod": true,
    "enforceInitMethod": true,
    "lazyInit": false,
    "lenientConstructorResolution": true,
    "methodOverrides": {
      "empty": true,
      "overrides": []
    },
    "nonPublicAccessAllowed": true,
    "primary": false,
    "propertyValues": {
      "converted": false,
      "empty": true,
      "propertyValueList": [],
      "propertyValues": []
    },
    "prototype": false,
    "qualifiers": [],
    "resolvableType": {
      "array": false,
      "componentType": {
        "$ref": "@"
      },
      "generics": [],
      "interfaces": [],
      "source": {
        "typeName": "org.springframework.core.ResolvableType$EmptyType@1f021e6c"
      },
      "superType": {
        "$ref": "@"
      },
      "type": {
        "$ref": "$.resolvableType.source"
      }
    },
    "resolvedAutowireMode": 0,
    "resource": {
      "description": "class path resource [readJson.xml]",
      "file": "D:\\code\\IDEA_workspace\\spring-learn\\spring-demo1\\target\\classes\\readJson.xml",
      "filename": "readJson.xml",
      "open": false,
      "path": "readJson.xml",
      "readable": true,
      "uRI": "file:/D:/code/IDEA_workspace/spring-learn/spring-demo1/target/classes/readJson.xml",
      "uRL": "file:/D:/code/IDEA_workspace/spring-learn/spring-demo1/target/classes/readJson.xml"
    },
    "resourceDescription": "class path resource [readJson.xml]",
    "role": 0,
    "scope": "",
    "singleton": true,
    "synthetic": false
  },
  {
    "abstract": false,
    "autowireCandidate": true,
    "autowireMode": 0,
    "beanClass": "com.springframework.learn.readjson.TestController",
    "beanClassName": "com.springframework.learn.readjson.TestController",
    "constructorArgumentValues": {
      "argumentCount": 1,
      "empty": false,
      "genericArgumentValues": [],
      "indexedArgumentValues": {
        "0": {
          "converted": false,
          "value": {
            "beanName": "testService",
            "toParent": false
          }
        }
      }
    },
    "dependencyCheck": 0,
    "enforceDestroyMethod": true,
    "enforceInitMethod": true,
    "lazyInit": false,
    "lenientConstructorResolution": true,
    "methodOverrides": {
      "empty": true,
      "overrides": []
    },
    "nonPublicAccessAllowed": true,
    "primary": false,
    "propertyValues": {
      "converted": false,
      "empty": true,
      "propertyValueList": [],
      "propertyValues": []
    },
    "prototype": false,
    "qualifiers": [],
    "resolvableType": {
      "array": false,
      "componentType": {
        "$ref": "@"
      },
      "generics": [],
      "interfaces": [],
      "source": {
        "typeName": "org.springframework.core.ResolvableType$EmptyType@1f021e6c"
      },
      "superType": {
        "$ref": "@"
      },
      "type": {
        "$ref": "$.resolvableType.source"
      }
    },
    "resolvedAutowireMode": 0,
    "resource": {
      "description": "class path resource [readJson.xml]",
      "file": "D:\\code\\IDEA_workspace\\spring-learn\\spring-demo1\\target\\classes\\readJson.xml",
      "filename": "readJson.xml",
      "open": false,
      "path": "readJson.xml",
      "readable": true,
      "uRI": "file:/D:/code/IDEA_workspace/spring-learn/spring-demo1/target/classes/readJson.xml",
      "uRL": "file:/D:/code/IDEA_workspace/spring-learn/spring-demo1/target/classes/readJson.xml"
    },
    "resourceDescription": "class path resource [readJson.xml]",
    "role": 0,
    "scope": "",
    "singleton": true,
    "synthetic": false
  }
]
```

刚才发现fastjson转换json为java对象的时候，如果对象里面对属性没有设置get、set值为空的，所以**constructorArgumentValues**是空的，所以代码逻辑有问题，解决办法。重新编译spring-beans的源码。

更新高版本的试下。结果还是不行。

有两种解决办法。
* 再构造一个constructorArgumentValuesTest，然后里面设置get、set方法，再去fastjson反序列一遍，然后把constructorArgumentValuesTest的值，赋值给constructorArgumentValues。

* 再构造一个constructorArgumentValuesTest，但是里面的属性ValueHolder用constructorArgumentValues的公共内部类，这样就可以直接用BeanUtils.copyProperties(object1,object2);这样去赋值。方便。

```java
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    public void sing(){
        testService.sing();
    }

    public TestService getTestService() {
        return testService;
    }
}
```
```java
public class TestService {

    public void sing(){
        System.out.println("天都黑了，你都没来过，没来坐");
    }
}
```
context
```java
public class ClassPathJsonApplicationContext extends AbstractRefreshableConfigApplicationContext {


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
        JsonBeanDefinitionReader beanDefinitionReader = new JsonBeanDefinitionReader(beanFactory);

        beanDefinitionReader.setEnvironment(this.getEnvironment());
        beanDefinitionReader.setResourceLoader(this);
        loadBeanDefinitions(beanDefinitionReader);
    }

    private void loadBeanDefinitions(JsonBeanDefinitionReader beanDefinitionReader) {
        String[] configurations = getConfigLocations();
        if (configurations!=null){
            beanDefinitionReader.loadBeanDefinitions(configurations);
        }
    }

    public ClassPathJsonApplicationContext(String configuration)throws BeansException{
        this(new String[]{configuration},true,null);
    }

    public ClassPathJsonApplicationContext(String[] configuration,boolean refresh,ApplicationContext parent)throws BeansException{
        super(parent);
        setConfigLocations(configuration);
        if (refresh){
            refresh();
        }
    }
}
```
jsonBeanDefinitionReader
```java
@Slf4j
public class JsonBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private final ThreadLocal<Set<EncodedResource>> resourcesCurrentlyBeingLoaded =
            new NamedThreadLocal<Set<EncodedResource>>("XML bean definition resources currently being loaded"){
                @Override
                protected Set<EncodedResource> initialValue() {
                    return new HashSet<>(4);
                }
            };

    protected JsonBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        Set<EncodedResource> currentResources = this.resourcesCurrentlyBeingLoaded.get();
        if (currentResources == null){
            currentResources = new HashSet<>(4);
            this.resourcesCurrentlyBeingLoaded.set(currentResources);
        }
        EncodedResource encodedResource = new EncodedResource(resource);
        if (!currentResources.add(encodedResource)){
            throw new BeanDefinitionStoreException(
                    "Detected cyclic loading of " + encodedResource + " - check your import definitions!");
        }
        String json = null;
        try(InputStream inputStream = encodedResource.getResource().getInputStream()){
            json = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        }catch (IOException e){
            log.error("抛出异常：",e);
            return 0;
        }finally {
            currentResources.remove(encodedResource);
            if (currentResources.isEmpty()){
                this.resourcesCurrentlyBeingLoaded.remove();
            }
        }
        Map<String,Object> originMap = new HashMap<>();
        List<BeanDefinitionTest> list1 = JSON.parseArray(json,BeanDefinitionTest.class);
        if (!CollectionUtils.isEmpty(list1)){
            for (BeanDefinitionTest beanDefinitionTest:list1) {
                String key = beanDefinitionTest.getBeanClassName();
                ConstructorArgumentValuesTest constructorArgumentValuesTest = beanDefinitionTest.getConstructorArgumentValues();
                if (!constructorArgumentValuesTest.isEmpty()){
                    originMap.put(key,constructorArgumentValuesTest);
                }
            }
        }


        //熟悉的fastjson，熟悉的味道
        List<GenericBeanDefinition> list = JSON.parseArray(json,GenericBeanDefinition.class);
        if (CollectionUtils.isEmpty(list)){
            return 0;
        }
        /*
        1、因为GenericBeanDefinition，只有setBeanClassName，所以bean反序列化时，只序列化了这个字段
        beanClass很重要，还有单独处理
         */
        for(GenericBeanDefinition genericBeanDefinition:list){
            Class<?> clazz = null;
            try{
                clazz = Thread.currentThread().getContextClassLoader().loadClass(genericBeanDefinition.getBeanClassName());
            }catch (ClassNotFoundException e){
                log.error("bean class cant be load for beandefinition: {}",genericBeanDefinition);
                throw new RuntimeException();
            }
            genericBeanDefinition.setBeanClass(clazz);

            //TODO 自己定义一个map去存储 key为beanClassName，value为ConstructorArgumentValues。
            ConstructorArgumentValuesTest constructorArgumentValues = (ConstructorArgumentValuesTest) originMap.get(genericBeanDefinition.getBeanClassName());
            if (constructorArgumentValues==null){
                continue;
            }
//          ConstructorArgumentValues constructorArgumentValues = genericBeanDefinition.getConstructorArgumentValues();
            if (constructorArgumentValues.isEmpty()){
                continue;
            }
            ConstructorArgumentValues constructorArgumentValues1 = new ConstructorArgumentValues();
            BeanUtils.copyProperties(constructorArgumentValues,constructorArgumentValues1);
            for (Integer key:constructorArgumentValues.getIndexedArgumentValues().keySet()){
//                Object value = valueHolder.getValue();
                constructorArgumentValues1.addIndexedArgumentValue(key,constructorArgumentValues.getIndexedArgumentValues().get(key));
            }
            Map<Integer,ConstructorArgumentValues.ValueHolder> map = constructorArgumentValues1.getIndexedArgumentValues();
            if (CollectionUtils.isEmpty(map)){
                continue;
            }
            for (ConstructorArgumentValues.ValueHolder valueHolder:map.values()){
                Object value = valueHolder.getValue();
                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(value);
//                JSONObject jsonObject1 = jsonObject.getJSONObject("value");
                RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(jsonObject.getString("beanName"));
                valueHolder.setValue(runtimeBeanReference);
            }
            genericBeanDefinition.setConstructorArgumentValues(constructorArgumentValues1);
        }
        setBeanNameGenerator(new AnnotationBeanNameGenerator());
        BeanNameGenerator beanNameGenerator = getBeanNameGenerator();
        BeanDefinitionRegistry registry = getRegistry();
        for(GenericBeanDefinition beanDefinition:list){
            String beanName = beanNameGenerator.generateBeanName(beanDefinition,registry);
            registry.registerBeanDefinition(beanName,beanDefinition);
        }
        return list.size()


                ;
    }
}
```
ConstructorArgumentValuesTest
```java
public class ConstructorArgumentValuesTest {

    private Map<Integer, ConstructorArgumentValues.ValueHolder> indexedArgumentValues = new LinkedHashMap<>();

    private List<ConstructorArgumentValues.ValueHolder> genericArgumentValues = new ArrayList<>();

    public Map<Integer, ConstructorArgumentValues.ValueHolder> getIndexedArgumentValues() {
        return indexedArgumentValues;
    }

    public void setIndexedArgumentValues(Map<Integer, ConstructorArgumentValues.ValueHolder> indexedArgumentValues) {
        this.indexedArgumentValues = indexedArgumentValues;
    }

    public List<ConstructorArgumentValues.ValueHolder> getGenericArgumentValues() {
        return genericArgumentValues;
    }

    public void setGenericArgumentValues(List<ConstructorArgumentValues.ValueHolder> genericArgumentValues) {
        this.genericArgumentValues = genericArgumentValues;
    }

    public boolean isEmpty() {
        return (this.indexedArgumentValues.isEmpty() && this.genericArgumentValues.isEmpty());
    }
}
```