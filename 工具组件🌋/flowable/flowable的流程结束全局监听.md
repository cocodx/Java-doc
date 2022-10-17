#### 流程结束全局监听

##### 监听器类集成AbstractFlowableEngineEventListener#processCompleted
重写processCompleted

```java
package com.yamcanda.icube.bpm.listener;

import com.yamcanda.icube.bpm.entity.BpmProcInstTypeExt;
import com.yamcanda.icube.bpm.enums.TaskTypeEnum;
import com.yamcanda.icube.bpm.service.BpmTaskTypeService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEntityEvent;
import org.flowable.engine.delegate.event.AbstractFlowableEngineEventListener;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 流程结束全局监听器
 */
@Slf4j
@Component
public class GlobalProcInstEndListener extends AbstractFlowableEngineEventListener {

    @Autowired
    private BpmTaskTypeService bpmTaskTypeService;

    @Override
    protected void processCompleted(FlowableEngineEntityEvent event) {
        log.debug("进入流程完成监听器----------------start---------------");
        String eventName = event.getType().name();

        FlowableEntityEventImpl flowableEntityEvent = (FlowableEntityEventImpl) event;
        ExecutionEntityImpl processInstance = (ExecutionEntityImpl) flowableEntityEvent.getEntity();

        Date startTime = processInstance.getStartTime();
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        String processInstanceId = processInstance.getProcessInstanceId();
        String processInstanceBusinessKey = processInstance.getProcessInstanceBusinessKey();
        int suspensionState = processInstance.getSuspensionState();


        log.debug("流程事件类型->{}", eventName);
        log.debug("流程开始时间->{}", startTime);
        log.debug("流程定义Key->{}", processDefinitionKey);
        log.debug("流程实例ID->{}", processInstanceId);
        log.debug("流程业务key->{}", processInstanceBusinessKey);
        log.debug("流程是否挂起标志->{}", suspensionState);

        BpmProcInstTypeExt bpmProcInstTypeExt = BpmProcInstTypeExt.builder().procInstId(processInstanceId).procInstType(TaskTypeEnum.GD.toString()).build();
        bpmTaskTypeService.addProcInst(bpmProcInstTypeExt);

        log.debug("流程完成监听器------------------------End---------------------->");
    }
}

```

##### 将监听器类加入到flowable的configuration类中
```java
 FlowableEventDispatcher eventDispatcher = configuration.getEventDispatcher();
//流程结束全局监听
eventDispatcher.addEventListener(globalProcInstEndListener, FlowableEngineEventType.PROCESS_COMPLETED);
```
