#### 背景介绍
我在查待办和已办的任务的节点的时候，sql语句是内连接了act_ru_execution的，获取这个表的字段act_id_（节点id）。

但是呢，新增任务的时候，executionId为空，没法跟act_ru_execution连表查询，为此，我就在新增任务之前，往act_ru_execution,还把内连接改成了左连接。

但是呢，这个act_ru_execution这个表的数据不能乱加。

#### 造成的后果

就是在前加签、后加签、加办，这些个新任务。一旦审批完成，同节点其他的任务都被Flowable自动完成了，在delete_reason_字段的值时MI_END，表示未完成任务，自动跳过。

暂时还不知道，为啥会造成这样，act_ru_execution这个表，我还没研究清楚。
