#### flowable用户任务设置集合变量不设置通过条件

**结论：需要所有用户都完成任务，才能到达下一个节点**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:flowable="http://flowable.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.flowable.org/processdef">
  <process id="ceshi3key" name="ceshi3" isExecutable="true">
    <startEvent id="startEvent1" flowable:formFieldValidation="true"></startEvent>
    <userTask id="sid-1F6C119B-C99F-419F-AC5D-AFB7F5B01AE6" name="审批1" flowable:assignee="${user}" flowable:formFieldValidation="true">
      <extensionElements>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
      <multiInstanceLoopCharacteristics isSequential="false" flowable:collection="userList1" flowable:elementVariable="user"></multiInstanceLoopCharacteristics>
    </userTask>
    <sequenceFlow id="sid-196A5B42-9346-485F-97B0-23A78AEDA016" sourceRef="startEvent1" targetRef="sid-1F6C119B-C99F-419F-AC5D-AFB7F5B01AE6"></sequenceFlow>
    <userTask id="sid-D057B4F5-20E2-4072-AA1F-2BD296A25F4F" name="审批2" flowable:assignee="${user}" flowable:formFieldValidation="true">
      <extensionElements>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
      <multiInstanceLoopCharacteristics isSequential="false" flowable:collection="userList2" flowable:elementVariable="user"></multiInstanceLoopCharacteristics>
    </userTask>
    <sequenceFlow id="sid-31646E15-A794-4B3B-A285-463BE11B54C5" sourceRef="sid-1F6C119B-C99F-419F-AC5D-AFB7F5B01AE6" targetRef="sid-D057B4F5-20E2-4072-AA1F-2BD296A25F4F"></sequenceFlow>
    <userTask id="sid-0451779A-A868-4F1C-AA49-FCC27257B374" name="审批3" flowable:assignee="${user}" flowable:formFieldValidation="true">
      <extensionElements>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
      <multiInstanceLoopCharacteristics isSequential="false" flowable:collection="userList3" flowable:elementVariable="user"></multiInstanceLoopCharacteristics>
    </userTask>
    <sequenceFlow id="sid-7E6A9712-B2C1-4F88-83F5-219FBBA784BC" sourceRef="sid-D057B4F5-20E2-4072-AA1F-2BD296A25F4F" targetRef="sid-0451779A-A868-4F1C-AA49-FCC27257B374"></sequenceFlow>
    <endEvent id="sid-EB713218-6A30-4398-AD41-152EC5CAF3D5"></endEvent>
    <sequenceFlow id="sid-805810DA-57B4-455E-ACD7-64163BF3BC0B" sourceRef="sid-0451779A-A868-4F1C-AA49-FCC27257B374" targetRef="sid-EB713218-6A30-4398-AD41-152EC5CAF3D5"></sequenceFlow>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_ceshi3key">
    <bpmndi:BPMNPlane bpmnElement="ceshi3key" id="BPMNPlane_ceshi3key">
      <bpmndi:BPMNShape bpmnElement="startEvent1" id="BPMNShape_startEvent1">
        <omgdc:Bounds height="30.0" width="30.0" x="100.0" y="163.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-1F6C119B-C99F-419F-AC5D-AFB7F5B01AE6" id="BPMNShape_sid-1F6C119B-C99F-419F-AC5D-AFB7F5B01AE6">
        <omgdc:Bounds height="80.0" width="100.0" x="285.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-D057B4F5-20E2-4072-AA1F-2BD296A25F4F" id="BPMNShape_sid-D057B4F5-20E2-4072-AA1F-2BD296A25F4F">
        <omgdc:Bounds height="80.0" width="100.0" x="510.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-0451779A-A868-4F1C-AA49-FCC27257B374" id="BPMNShape_sid-0451779A-A868-4F1C-AA49-FCC27257B374">
        <omgdc:Bounds height="80.0" width="100.0" x="720.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-EB713218-6A30-4398-AD41-152EC5CAF3D5" id="BPMNShape_sid-EB713218-6A30-4398-AD41-152EC5CAF3D5">
        <omgdc:Bounds height="28.0" width="28.0" x="945.0" y="164.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sid-7E6A9712-B2C1-4F88-83F5-219FBBA784BC" id="BPMNEdge_sid-7E6A9712-B2C1-4F88-83F5-219FBBA784BC">
        <omgdi:waypoint x="609.9499999999504" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="719.999999999997" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-805810DA-57B4-455E-ACD7-64163BF3BC0B" id="BPMNEdge_sid-805810DA-57B4-455E-ACD7-64163BF3BC0B">
        <omgdi:waypoint x="819.9499999999675" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="945.0" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-196A5B42-9346-485F-97B0-23A78AEDA016" id="BPMNEdge_sid-196A5B42-9346-485F-97B0-23A78AEDA016">
        <omgdi:waypoint x="129.9499996223143" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="284.9999999998994" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-31646E15-A794-4B3B-A285-463BE11B54C5" id="BPMNEdge_sid-31646E15-A794-4B3B-A285-463BE11B54C5">
        <omgdi:waypoint x="384.94999999996566" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="510.0" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```

在启动的时候，加入变量如下：
```java
ProcessEngine processEngine = configuration.buildProcessEngine();

//我们需要通过RuntimeService来启动流程实例
RuntimeService runtimeService = processEngine.getRuntimeService();
//构建流程实例
Map<String,Object> variables = new HashMap<>();
variables.put("userList1",Arrays.asList("6","7"));
variables.put("userList2",Arrays.asList("8","9"));
variables.put("userList3",Arrays.asList("10"));
//启动流程实例
ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKey("ceshi3key", variables);
//        ProcessInstance holidayRequest = runtimeService.startProcessInstanceByKeyAndTenantId("myTestKey1234", variables, "user1");
System.out.println("holidayRequest.getProcessDefinitionId(); = " + holidayRequest.getProcessDefinitionId());
System.out.println("holidayRequest.getActivityId(); = " + holidayRequest.getActivityId());
System.out.println("holidayRequest.getId(); = " + holidayRequest.getId());
```

当只有6,7都通过了才能达到下一个审批节点
![image](https://user-images.githubusercontent.com/97614802/195281260-b62b989f-6b36-437e-9038-b3833d85f44f.png)

![image](https://user-images.githubusercontent.com/97614802/195281295-e9aafd0b-29a1-47ee-9c20-d45ad0e8775f.png)

