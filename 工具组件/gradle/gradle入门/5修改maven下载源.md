在这个文件夹下创建.gradle结尾的初始化脚本

D:\code\gradle-7.4\init.d

```gradle
allprojects {
    repositories {
        mavenLocal()
        maven { name "Alibaba" ; url "https://maven.aliyun.com/repository/public" }
        maven { name "Bstek" ; url "http://nexus.bsdn.org/content/groups/public/" }
        mavenCentral()
    }

    buildscript { 
        repositories { 
            maven { name "Alibaba" ; url 'https://maven.aliyun.com/repository/public' }
            maven { name "Bstek" ; url 'http://nexus.bsdn.org/content/groups/public/' }
            maven { name "M2" ; url 'https://plugins.gradle.org/m2/' }
        }
    }
}
```
要配置一个M2_HOME环境变量
![image](../../../images/Snipaste_2022-07-10_03-47-25.png)

阿里云仓库地址 https://developer.aliyun.com/mvn/guide
![image](../../../images/Snipaste_2022-07-10_03-58-55.png)