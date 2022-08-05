##### jetty
jetty是一个嵌入式服务器，直接通过pom依赖，集成进入到项目中，本篇介绍如何在 maven项目中 启动jetty

1. 将pom的打包类型设置为war
2. 引入jetty10.0的依赖，11.0不支持servlet4.0
3. 引入servlet4.0的依赖

```xml
<packaging>war</packaging>

<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
</dependency>

<build>
    <plugins>
        <plugin>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-maven-plugin</artifactId>
            <version>10.0.11</version>
            <configuration>
                <httpConnector>
                    <port>8080</port><!-- 端口号,非必填 -->
                </httpConnector>
                <path>/diary</path><!-- 根路径,非必填 -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

进入当前项目的文件浏览器窗口，cmd打开widows命令行。输入 mvn jetty:run

然后访问localhost:8080

下图是我，实验的时候遇到的报错
![image](https://user-images.githubusercontent.com/97614802/182993338-994e8f1b-08d9-4697-b67f-29e7342604da.png)

![image](https://user-images.githubusercontent.com/97614802/182993369-b7b6546e-c39c-4046-8b66-b2622b1a91a1.png)
