# 1.流程管理器
## 1.1流程的用户任务变量设置

![image](https://user-images.githubusercontent.com/97614802/198276634-cc8b1b14-8794-48f9-a1b9-ed393b27d403.png)

![image](https://user-images.githubusercontent.com/97614802/198277157-fb72cbd0-ead7-41b4-99ff-bbf56d17d42d.png)

类型：Parallel
当userList1=> 10,12的用户id。同时生成任务

类型：Sequential

当userList1=> 10,12的用户id。依次生成。

![image](https://user-images.githubusercontent.com/97614802/198277352-f9185fc0-33ea-48c5-b2cc-4b34d5ed7078.png)
![image](https://user-images.githubusercontent.com/97614802/198277509-57aea96f-9e55-4a16-a991-1b692b7b751b.png)

委托myTaskListener 表示 调用spring容器里面的beanName。

## 1.2排他网关
![image](https://user-images.githubusercontent.com/97614802/198279010-adca4261-566e-47eb-8c33-35059921bc4c.png)

出来的箭头要设置条件必须要有true。多个箭头为的判断为true也没有关系。默认使用第一个。不能没有true。全是false的话，流程走不下去。

![image](https://user-images.githubusercontent.com/97614802/198279520-2eb6898b-491f-4b53-b441-c88573e92f95.png)

${} ：flowable进行识别括号里面的内容

days ： 就是提交时候的变量
![image](https://user-images.githubusercontent.com/97614802/198279905-ce6556d8-d489-4459-9e28-90d6e6523aa7.png)



