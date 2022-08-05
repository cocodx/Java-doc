1.添加本地仓库路径。保存
2.添加mirror镜像。保存

```xml
<localRepository>D:/m2/repo</localRepository>

<mirror>
  <id>nexus-aliyun</id>
  <mirrorOf>central</mirrorOf>
  <name>Nexus aliyun</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```
