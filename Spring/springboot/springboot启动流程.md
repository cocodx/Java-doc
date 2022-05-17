![image](../../images/Snipaste_2022-05-15_17-44-17.png)  

集成springmvc，引入starter-web，启动自动配置类。
@ConditionOnClass  
@Bean 
xxxxProperties  

集成mybatis，引入mybatis-starter，包含自动配置类。  

DispatcherServlet拦截所有的http请求，进行分发。分发给对应的controller进行处理。必须依赖在servlet里面，servlet容器。    
ContextLoaderListener可以脱离tomcat，因为它只是去加载了spring的上下文。  

#### 1、为什么springboot的jar可以直接运行？  
1. 首先添加springboot的maven插件，不添加则会没有主清单的属性。里面有一个MAINFEST.MF文件，并且把我们依赖的jar包都打进去了。fat-jar。这样还是没法直接运行的，因为Java没有提供任何报标准的方式加载嵌套的jar文件。（jar文件的jar）里面还有一个Main-Class：org.springframework.boot.loader.JarLauncher.
 
当我们运行java -jar，帮我们找到jar文件中mainfest文件，在那里面找到真正的启动类。

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-loader</artifactId>
    <version>2.6.7</version>
</dependency>
```

在idea里面使用jar application进行调试。  
总结：  
JarLauncher通过加载BOOT-INF/classes目录及BOOT-INF/lib目录下jar文件，实现了fat jar的启动。 SpringBoot通过扩展JarFile、JarURLConnection及URLStreamHandler，实现了jar in jar中资源的加载。 SpringBoot通过扩展URLClassLoader–LauncherURLClassLoader，实现了jar in jar中class文件的加载。 

WarLauncher通过加载WEB-INF/classes目录及WEB-INF/lib和WEB-INF/lib-provided目录下的jar文件，实现了war文 件的直接启动及web容器中的启动。
