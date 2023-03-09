#### 服务任务

也就是调用java项目中的bean，根据flowable的超时一样，也是要绑定一个java的bean对象。

![image](https://user-images.githubusercontent.com/97614802/224007942-d9c22a9e-2212-44f0-9cd6-3069e9e1e654.png)

用法：只需要在箭头出口处，添加一个服务任务。

![image](https://user-images.githubusercontent.com/97614802/224008277-991c8dc5-7bc3-4411-bf70-7e6dd5a579fa.png)

然后，在服务任务中，关联bean的地方，添加关联的javabean的名称即可。可以直接写类的位置，在做测试的时候。在项目中使用需要
使用${}符号包裹bean名称即可。如图所示

![image](https://user-images.githubusercontent.com/97614802/224008638-63696bc3-1c3b-435a-80ca-ed6c7050fb95.png)

同时设置为异步，服务任务不会阻塞流程，增加一个异步线程去执行这个服务任务。




