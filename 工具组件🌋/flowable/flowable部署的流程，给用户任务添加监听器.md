#### flowable的流程图中，不添加监听器，部署的时候，统一添加监听器。

在flowable中，一个一个的去加监听器太麻烦了且繁琐，所以，想找到一种方式去进行全局的添加。

基本原理：在调用flowable的流程部署之后，获取到信息，取到用户任务，加上监听器，重去部署一次。

部署代码：
```java
Deployment deployment = repositoryService.createDeployment().addBytes(processName, bpmnBytes)
    .name(model.getName()).key(model.getKey()).tenantId(tenantId).deploy();
```
部署之后，再根据部署Id查到ActReProcDef，获取到BpmnModel
```java
ActReProcDef actReProcDef = actReProcdefMapper.findProcessDefinitionById(deployment.getId());

BpmnModel bpmnModel = repositoryService.getBpmnModel(actReProcDef.getId());
```

获取到BpmnModel，继续获取里面的UserTask，添加上监听器
```java
BpmnModel bpmnModel = repositoryService.getBpmnModel(actReProcDef.getId());
String prefixName = "usertask";
Object object = null;
int i = 1;
do {
    FlowElement flowElement = bpmnModel.getFlowElement(prefixName + i);
    object = flowElement;
    if (!ObjectUtils.isEmpty(flowElement)) {
        UserTask userTask = (UserTask)flowElement;
        if (!userTask.getName().equals(BpmConst.FLOW_SUBMITTER)) {
            userTask.getTaskListeners().add(MyTaskListenerBpmnParseHandler.createTaskListener());
            userTask.getExecutionListeners().add(MyTaskListenerBpmnParseHandler.endExecutionListener());
        }
    }
    i++;
} while (!ObjectUtils.isEmpty(object));
```
加上之后，再去进行流程部署，然后将新部署的流程信息，记录到自己创建的查询表里面，但是这样它的version，每次都跳两下

```java
Deployment newTaskDeployment =
            repositoryService.createDeployment().addBpmnModel(deployment.getId() + ".bpmn", bpmnModel)
                .name(model.getName()).key(model.getKey()).tenantId(tenantId).deploy();
```

除了加监听器之外，还可以获取到 SqenceFlow上的条件，然后将对应的参数添加到你的参数表里面。


