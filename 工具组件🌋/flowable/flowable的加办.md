#### 加办

现象：在我处理的任务里面，点击加办，选择办理的用户人，这些加办的人，办不办都行。但是我的任务审批完成，加办人的任务也会自动审批完成。

![image](https://user-images.githubusercontent.com/97614802/212584637-a7d39779-d7b0-4a60-a240-4a7536e396f8.png)

就是给当前的任务，添加子任务，设置parent_task_id_

scope_type_ 这个是设置after，before。标识后加签，前加签。在调用flowable的complete审批完成接口。就不会去审批创建的这个子任务。

综上所述：加办，只是给这个任务添加两条子数据
