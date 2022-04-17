当我们用java命令运行某个类的main函数启动程序时，首先需要通过**类加载器**把主类加载到JVM。

```java
public class Math {

    public static int initData = 666;
    public static User user = new User();

    public int compute(){ //一个方法对应一个栈帧内存区域
        int a=1;
        int b=2;
        int c = (a+b)*10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}

```
![image](../images/Snipaste_2022-04-18_07-07-13.png)
其中loadClass的类加载过程主要有5步：
**加载 >> 验证 >> 准备 >> 解析 >> 初始化 >>** 使用 >> 卸载
1. 加载：在硬盘上查找并通过IO读入字节码文件，使用到类时才会加载，例如调用类的main()方法，new对象等等，在加载阶段会在内存中生成一个**代表这个类的java.lang.class对象**，作为方法区这个类的各种数据的访问入口
1. 验证：校验字节码文件的正确性
1. 准备：给类的静态变量分配内存，并赋予默认值
1. 解析：将**符号引用**替换为直接引用，该阶段会把一些静态方法（符号引用，比如main()方法）替换为指向数据所存内存的指针或句柄等（直接引用），这是所谓的**静态链接**过程（类加载期间完成），**动态链接**是在程序运行期间完成的将符号引用替换为直接引用。多态，接口有不同的实现。
1. 初始化：对类的静态变量初始化为指定的值，执行静态代码块
![image](../images/类加载的主要流程阶段.png)

类被加载到方法区中后主要包含 **运行时常量池，类型信息，字段信息，方法信息，类加载器的引用，对应class实例的引用**，等信息。
**类加载器的引用**：这个类到类加载器实例的引用
**对应class实例的引用**：类加载器在加载类信息放到方法区中后，会创建一个对应的Class类型的对象实例放到堆（Heap）中，做为开发人员访问方法区中类定义的入口和切入点。

注意：JVM里面的类加载，懒加载，一般是不会去加载它们，直到用到了它们。逐步加载这些类。
jar包或war包里的类不是一次性全部加载的，是使用到时才加载。
```java
public class TestDynamicLoad {

    static{
        System.out.println("*************load TestDymicLoad************************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("**********************load test******************************");
        B b = null;//B不会加载，除非这里执行new B()
    }
}
class A{
    static {
        System.out.println("load A");
    }

    public A(){
        System.out.println("initial A");
    }
}
class B{
    static {
        System.out.println("load B");
    }

    public B(){
        System.out.println("initial B");
    }
}

```
运行结果如下：
```powershell
*************load TestDymicLoad************************
load A
initial A
**********************load test******************************
```
如果只是定义一个变量b，是不会去加载类，更不会去初始化类B

#### 类加载器和双亲委派机制
上面的类加载过程主要是通过类加载器来实现的，Java里有如下几种类加载器

* 引导类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的核心类库，比如rt.jar、charsets.jar等
* 扩展类加载器：负责加载支撑JVM运行的位于JRE的lib目录下的ext扩展目录中的JAR类包
* 应用程序类加载器：负责加载ClassPath路径下的类包，主要就是加载你自己写的那些类
* 自定义加载器：负责加载用户自定义路径下的类包

```java
public class TestJDKClassLoader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        System.out.println(TestJDKClassLoader.class.getClassLoader());

        System.out.println("\n");
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootStrapLoader = extClassLoader.getParent();
        System.out.println("the bootStrapLoader: "+bootStrapLoader);
        System.out.println("the extClassLoader: "+extClassLoader);
        System.out.println("the appClassLoader: "+appClassLoader);

        System.out.println("\n");
        System.out.println("bootstrapLoader加载以下文件： ");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println("\n");
        System.out.println("extClassLoader加载以下文件： ");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("\n");
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }
}
```

运行结果如下：
```powershell
null
sun.misc.Launcher$ExtClassLoader@61bbe9ba
sun.misc.Launcher$AppClassLoader@18b4aac2


the bootStrapLoader: null
the extClassLoader: sun.misc.Launcher$ExtClassLoader@61bbe9ba
the appClassLoader: sun.misc.Launcher$AppClassLoader@18b4aac2

bootstrapLoader加载以下文件： 
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/lib/resources.jar
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/lib/rt.jar
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/lib/sunrsasign.jar
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/lib/jsse.jar
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/lib/jce.jar
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/lib/charsets.jar
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/lib/jfr.jar
file:/C:/Program%20Files/java/jdk1.8.0_144/jre/classes


extClassLoader加载以下文件： 
C:\Program Files\Java\jdk1.8.0_144\jre\lib\ext;C:\WINDOWS\Sun\Java\lib\ext


appClassLoader加载以下文件：
C:\Program Files\java\jdk1.8.0_144\jre\lib\charsets.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\deploy.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\access-bridge-64.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\cldrdata.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\dnsns.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\jaccess.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\jfxrt.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\localedata.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\nashorn.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\sunec.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\sunjce_provider.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\sunmscapi.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\sunpkcs11.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\ext\zipfs.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\javaws.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\jce.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\jfr.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\jfxswt.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\jsse.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\management-agent.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\plugin.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\resources.jar;C:\Program Files\java\jdk1.8.0_144\jre\lib\rt.jar;D:\code\IDEA_workspace\jvm-learn\target\classes;D:\JetBrains\IntelliJ IDEA 2019.2.3\lib\idea_rt.jar

```

类加载器初始化过程：
参见类运行加载全过程图可知其中会创建JVM启动器实例sun.misc.Launcher
sun.misc.Launcher初始化使用了饿汉单例模式设计，保证一个JVM虚拟机内只有一个sun.misc.Launcher实例。
在Launcher构造方法内部，其创建了两个类加载器，分别是sun.misc.Launcher.ExtClassLoader（扩展类加载器）和sun.misc.Launcher.AppClassLoader（应用类加载器）。
JVM默认使用Launcher的getClassLoader()方法返回的类加载器AppClassLoader实例加载我们的应用程序。

Launcher初始化代码
```java
public Launcher() {
    Launcher.ExtClassLoader var1;
    try {
        //返回extClassLoader，在构造的过程中将其父加载器设置为null
        var1 = Launcher.ExtClassLoader.getExtClassLoader();
    } catch (IOException var10) {
        throw new InternalError("Could not create extension class loader", var10);
    }

    try {
        //构造应用类加载器，在构造的过程中将其父加载器设置为ExtClassLoader
        //launcher的loader属性值是AppClassLoader，我们一般都是用这个类加载器来加载我们自己写的应用程序。
        this.loader = Launcher.AppClassLoader.getAppClassLoader(var1);
    } catch (IOException var9) {
        throw new InternalError("Could not create application class loader", var9);
    }

    Thread.currentThread().setContextClassLoader(this.loader);
    String var2 = System.getProperty("java.security.manager");
    if (var2 != null) {
        SecurityManager var3 = null;
        if (!"".equals(var2) && !"default".equals(var2)) {
            try {
                var3 = (SecurityManager)this.loader.loadClass(var2).newInstance();
            } catch (IllegalAccessException var5) {
            } catch (InstantiationException var6) {
            } catch (ClassNotFoundException var7) {
            } catch (ClassCastException var8) {
            }
        } else {
            var3 = new SecurityManager();
        }

        if (var3 == null) {
            throw new InternalError("Could not create SecurityManager: " + var2);
        }

        System.setSecurityManager(var3);
    }

}
public ClassLoader getClassLoader() {
    return this.loader;
}
```
![image](../images/Snipaste_2022-04-18_05-57-24.png)
为什么用应用程序类加载器来加载呢，web程序，百分之95%的代码，都是由应用程序类加载器加载的，第一次加载的时候确确实实是多走了一步，但是后面都不用了往上找了，如果直接用bootstrap类加载，那每次都要从上往下找。

加载某个类时，会先委托父加载器寻找目标类，找不到再委托上层父加载器加载，如果所有父加载器路径下都找不到目标类，则在自己的类加载路径中查找并载入目标类。
比如说我们的Math类，最先会找应用程序类加载器加载，应用程序类加载器会先委托扩展类加载器加载，扩展类加载器再委托引导类加载器

appClassLoader的父类继承关系
![image](../images/Snipaste_2022-04-18_06-11-56.png)
从我已加载的类集合里面，看下是不是加载过了，最终是调了C++代码native。

ClassLoader的loadClass代码
```java
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    //不为null就是找，extClassLoader
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    //为null，bootstrapLoader
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    //都会调用URLClassLoader的findClass方法在加载器的类路径里查找并加载该类
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
```

**为什么要设计双亲委派机制？**
* 沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心API库被随意篡改
* 避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次，保证被加载类的唯一性

自定义的String.class,和java的String包名一模一样
```java
package java.lang;

public class String {


    public static void main(String[] args) {
        System.out.println("My String class");
    }
}
```
运行结果如下：
```java
错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
   public static void main(String[] args)
否则 JavaFX 应用程序类必须扩展javafx.application.Application
```
java.lang.String.class被委托到引导类加载器，在rt.jar包里，全路径同名的类，找到了，加载成功，加载的jdk的类，里面没有main方法，找不到，就报错了。

全盘委托加载机制：这个Math会加载到其他的类，也会用AppClassLoader
当一个ClassLoader装载一个类时，除非显示的使用另外一个ClassLoader，该类所依赖及引用的类也由这个ClassLoader载入。

自定义类加载器示例：
自定义类加载器只需要实现loadClass和findClass，主要是**重写findClass方法**

自定义类加载器
```java

```

Tomcat双亲委派机制
![image](../images/Tomcat双亲委派机制.png)
自己搞不定，百度搞，谷歌搞，再去问别人搞。