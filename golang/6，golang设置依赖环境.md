#### golang设置依赖环境

跟java的maven依赖类似。
原生的ip地址，被国内的防火墙屏蔽了，需要设置为国内的依赖地址

报错如图所示：
![image](https://user-images.githubusercontent.com/97614802/203028225-60f8205b-d326-4513-b608-6c1c6722c9b0.png)

在cmd中，输入以下命令
```java
go env -w GOPROXY=https://goproxy.cn
```
