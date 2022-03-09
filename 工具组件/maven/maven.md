> maven是一个项目构建工具，方便管理依赖jar包

如何引入一个依赖jar包
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.11</version>
    <scope>test</scope>
</dependency>
```
gourpId+artifactId+version，可以构成一个坐标，从maven的远程仓库或者本地仓库引入jar包的依赖。

groupId：定义了项目属于哪个组，这个组往往和项目所在的组织和公司存在关联。譬如在googolecode上建立了一个myapp的项目，那么gourpId就应该是**com.googlecode.myapp**

artifactId:定义了当前Maven项目在组中的唯一ID，如myapp-util，myapp-domain，myapp-web

version：指定了myapp项目当前的版本-1.0.SNAPSHOT。SNAPSHOT意为快照，说明该项目还在开发中，是不稳定的版本。
