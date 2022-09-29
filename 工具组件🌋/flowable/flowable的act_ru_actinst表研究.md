![image](https://user-images.githubusercontent.com/97614802/193014438-b06a055b-58e3-4682-b255-501104cb45fe.png)

![image](https://user-images.githubusercontent.com/97614802/193018579-1af7904c-b32a-4270-b8a8-99f39e41ba27.png)


看起来是 真实的节点信息啊。

如图，第一个是开始事件，然后是箭头，然后是网关，然后是两个箭头，然后到了两个审批A，B。

第一个A审批，一个箭头到网关，第二个B审批，一个箭头到网关

最后一个箭头到，审批C

![image](https://user-images.githubusercontent.com/97614802/193017829-b183c30e-5167-4f13-9869-e376fe2420f8.png)

![image](https://user-images.githubusercontent.com/97614802/193017968-b98fa6db-7ee8-41ec-b2a0-97bdeedd4038.png)

![image](https://user-images.githubusercontent.com/97614802/193018721-85bfce65-3647-47b6-a611-2ece8320fe49.png)


end_time_ 是空的呢，就表示没有审批。

|字段|备注|
|-|-|
|id_|主键|
|rev_|版本|
|proc_def_id_|流程定义id|
|proc_inst_id_|流程实例id|
|execution_id_|暂时不清楚|
|act_id_|xml里面。对应的id|
|task_id_|暂时不清楚|
|act_type_|类型，parallelGateway表示并行网关，userTask表示用户任务|
|start_time_|开始时间|
|end_time_|结束时间|
|end_time_|结束时间|
|duration_|!!!这是啥，是唯一的吗|
|transaction_order_|感觉是事务id|
