##### 新建用户任务
新建用户任务：可以分配给流程发起者，这样的话他就可以直接通过了
![image](https://user-images.githubusercontent.com/97614802/191727650-56f93e45-02b1-4d49-b440-85bbc7a71921.png)

例如上图，请假审批就不用审批，直接过就完了，所以说要分配给流程发起者，直接过。操作如下

![image](https://user-images.githubusercontent.com/97614802/191728024-03bdf09d-aeab-4927-88ae-810223fa6a0b.png)

好像也不是这样，这样也不行

应该是设置默认流

![image](https://user-images.githubusercontent.com/97614802/191730857-8bfff4fc-caa2-48d8-97d9-ba78626934dd.png)

也不行，这个地方还是在这，应该默认直接给他过
![image](https://user-images.githubusercontent.com/97614802/191732069-7b2fd1f8-0d0c-4fad-aef3-fd5fc3bfa159.png)

直接查，用户为 $INITIATOR

处理逻辑：开启一个流程，之后，执行一个方法。完成 用户为 $INITIATOR 的任务

手动跑完了一个流程

![image](https://user-images.githubusercontent.com/97614802/191733944-04b2cf72-58de-4804-9703-456efba44650.png)
