启动任务，日志报错执行器地址null

可能执行器table表单的数据，不是自动注入插入的，而是初始化数据加的。

![image](https://user-images.githubusercontent.com/97614802/190946292-e6f16bba-5d0d-4642-8a86-030c236336b3.png)

![image](https://user-images.githubusercontent.com/97614802/190946331-a7320717-650b-4cd2-aef9-ed023a35758b.png)

所以，要手动再去编辑执行器的地址。executor相当于，job的http地址。

![image](https://user-images.githubusercontent.com/97614802/190946451-4da4c1cc-ad04-4f5a-9348-7c77f183bd1d.png)

执行器的默认地址端口 port：9999

是配置在job所在的java应用中。类似配置如下所示：

这是properties配置，用yml的话，就是有层级区别

```java
### 调度中心部署根地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调"；为空则关闭自动注册；
xxl.job.admin.addresses=http://127.0.0.1:8080/xxl-job-admin
### 执行器通讯TOKEN [选填]：非空时启用；
xxl.job.accessToken=
### 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
xxl.job.executor.appname=xxl-job-executor-sample
### 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 ”IP:PORT“ 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题。
xxl.job.executor.address=
### 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"；
xxl.job.executor.ip=
### 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口；
xxl.job.executor.port=9999
### 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径；
xxl.job.executor.logpath=/data/applogs/xxl-job/jobhandler
### 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能；
xxl.job.executor.logretentiondays=30
```


