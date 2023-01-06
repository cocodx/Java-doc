#### 名称：超时1小时-最新流程文件.bpmn20.xml

具体xml

```xml
<?xml version='1.0' encoding='UTF-8'?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:flowable="http://flowable.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.flowable.org/processdef">
  <process id="ceshi2-key18162775609" name="超时1H测试流程" isExecutable="true">
    <startEvent id="startEvent1" name="开始"/>
    <userTask id="usertask1" name="userList1审批" flowable:assignee="${user}">
      <extensionElements>
        <flowable:executionListener event="end" delegateExpression="${endExecutionListener}"/>
        <flowable:taskListener event="create" delegateExpression="${createTaskListener}"/>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
      <multiInstanceLoopCharacteristics isSequential="false" flowable:collection="userList1" flowable:elementVariable="user"/>
    </userTask>
    <sequenceFlow id="sid-78BB3E7A-5DBA-4ABE-AA57-B0CF17CF4B2B" sourceRef="startEvent1" targetRef="sid-EDF6EFF8-953D-4C51-A18F-62751B8D8BD1"/>
    <sequenceFlow id="sid-B403E80B-732B-4B2F-B24E-8D6CCEB5D2BC" sourceRef="usertask1" targetRef="sid-0067D45A-A955-4183-A4D8-9611EAF67E82"/>
    <endEvent id="sid-B973B0BB-C3A4-4D7F-BB44-60F84FD9E1C0" name="结束"/>
    <exclusiveGateway id="sid-0067D45A-A955-4183-A4D8-9611EAF67E82"/>
    <userTask id="usertask2" name="userList2审批" flowable:assignee="${user}">
      <extensionElements>
        <flowable:executionListener event="end" delegateExpression="${endExecutionListener}"/>
        <flowable:taskListener event="create" delegateExpression="${createTaskListener}"/>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
      <multiInstanceLoopCharacteristics isSequential="false" flowable:collection="userList2" flowable:elementVariable="user"/>
    </userTask>
    <sequenceFlow id="sid-ABE88442-9760-4A48-94E7-8756F0E18D06" sourceRef="usertask2" targetRef="sid-B973B0BB-C3A4-4D7F-BB44-60F84FD9E1C0"/>
    <userTask id="usertask3" name="userList3审批" flowable:assignee="${user}">
      <extensionElements>
        <flowable:executionListener event="end" delegateExpression="${endExecutionListener}"/>
        <flowable:taskListener event="create" delegateExpression="${createTaskListener}"/>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
      <multiInstanceLoopCharacteristics isSequential="false" flowable:collection="userList3" flowable:elementVariable="user"/>
    </userTask>
    <sequenceFlow id="sid-363CB303-D81F-46F4-91C3-5A42A9A19C82" sourceRef="usertask3" targetRef="sid-B973B0BB-C3A4-4D7F-BB44-60F84FD9E1C0"/>
    <sequenceFlow id="sid-C1F8A328-9A8D-4151-8A49-9D6B418EC19E" name="days大于1" sourceRef="sid-0067D45A-A955-4183-A4D8-9611EAF67E82" targetRef="usertask2">
      <conditionExpression xsi:type="tFormalExpression"><![CDATA[${days>1}]]></conditionExpression>
    </sequenceFlow>
    <sequenceFlow id="sid-1262DB2C-0B18-4D19-B604-BFB30977F8D8" name="days等于1" sourceRef="sid-0067D45A-A955-4183-A4D8-9611EAF67E82" targetRef="usertask3">
      <conditionExpression xsi:type="tFormalExpression"><![CDATA[${days==1}]]></conditionExpression>
    </sequenceFlow>
    <userTask id="sid-EDF6EFF8-953D-4C51-A18F-62751B8D8BD1" name="提交人" flowable:assignee="${initiator}" flowable:skipExpression="${initiator==''}">
      <extensionElements>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
    </userTask>
    <sequenceFlow id="sid-7CAC3F7E-668E-4466-B556-76730DCB68D4" sourceRef="sid-EDF6EFF8-953D-4C51-A18F-62751B8D8BD1" targetRef="usertask1"/>
    <boundaryEvent id="sid-29E4FA56-88CC-464A-9472-93E7735F2295" attachedToRef="usertask1" cancelActivity="true">
      <extensionElements>
        <flowable:executionListener event="end" delegateExpression="${processDueTimeListener}"/>
      </extensionElements>
      <timerEventDefinition>
        <timeDuration>PT1H</timeDuration>
      </timerEventDefinition>
    </boundaryEvent>
    <userTask id="usertask4" name="userList4审批" flowable:assignee="${user}">
      <extensionElements>
        <flowable:executionListener event="end" delegateExpression="${endExecutionListener}"/>
        <flowable:taskListener event="create" delegateExpression="${createTaskListener}"/>
        <modeler:initiator-can-complete xmlns:modeler="http://flowable.org/modeler"><![CDATA[false]]></modeler:initiator-can-complete>
      </extensionElements>
      <multiInstanceLoopCharacteristics isSequential="false" flowable:collection="userList4" flowable:elementVariable="user"/>
    </userTask>
    <sequenceFlow id="sid-975C60B4-1A4D-44A7-BE55-741B4D345DE4" sourceRef="sid-29E4FA56-88CC-464A-9472-93E7735F2295" targetRef="usertask4"/>
    <sequenceFlow id="sid-1486F0D2-F81C-44E6-8310-424E4C1231A3" sourceRef="usertask4" targetRef="sid-B973B0BB-C3A4-4D7F-BB44-60F84FD9E1C0"/>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_ceshi2-key123">
    <bpmndi:BPMNPlane bpmnElement="ceshi2-key123" id="BPMNPlane_ceshi2-key123">
      <bpmndi:BPMNShape bpmnElement="startEvent1" id="BPMNShape_startEvent1">
        <omgdc:Bounds height="30.0" width="30.0" x="30.0" y="163.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="usertask1" id="BPMNShape_usertask1">
        <omgdc:Bounds height="80.0" width="100.0" x="240.0" y="138.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-B973B0BB-C3A4-4D7F-BB44-60F84FD9E1C0" id="BPMNShape_sid-B973B0BB-C3A4-4D7F-BB44-60F84FD9E1C0">
        <omgdc:Bounds height="28.0" width="28.0" x="750.0" y="164.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-0067D45A-A955-4183-A4D8-9611EAF67E82" id="BPMNShape_sid-0067D45A-A955-4183-A4D8-9611EAF67E82">
        <omgdc:Bounds height="40.0" width="40.0" x="390.0" y="158.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="usertask2" id="BPMNShape_usertask2">
        <omgdc:Bounds height="80.0" width="100.0" x="525.0" y="138.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="usertask3" id="BPMNShape_usertask3">
        <omgdc:Bounds height="80.0" width="100.0" x="510.0" y="15.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-EDF6EFF8-953D-4C51-A18F-62751B8D8BD1" id="BPMNShape_sid-EDF6EFF8-953D-4C51-A18F-62751B8D8BD1">
        <omgdc:Bounds height="80.0" width="100.0" x="109.0" y="138.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-29E4FA56-88CC-464A-9472-93E7735F2295" id="BPMNShape_sid-29E4FA56-88CC-464A-9472-93E7735F2295">
        <omgdc:Bounds height="31.0" width="31.0" x="224.5" y="203.5"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="usertask4" id="BPMNShape_usertask4">
        <omgdc:Bounds height="80.0" width="100.0" x="510.0" y="255.0"/>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sid-1486F0D2-F81C-44E6-8310-424E4C1231A3" id="BPMNEdge_sid-1486F0D2-F81C-44E6-8310-424E4C1231A3">
        <omgdi:waypoint x="609.95" y="295.0"/>
        <omgdi:waypoint x="764.0" y="295.0"/>
        <omgdi:waypoint x="764.0" y="191.94993190462358"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-7CAC3F7E-668E-4466-B556-76730DCB68D4" id="BPMNEdge_sid-7CAC3F7E-668E-4466-B556-76730DCB68D4">
        <omgdi:waypoint x="208.9499999999503" y="178.0"/>
        <omgdi:waypoint x="239.99999999996172" y="178.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-78BB3E7A-5DBA-4ABE-AA57-B0CF17CF4B2B" id="BPMNEdge_sid-78BB3E7A-5DBA-4ABE-AA57-B0CF17CF4B2B">
        <omgdi:waypoint x="59.94999859402066" y="178.0"/>
        <omgdi:waypoint x="108.99999999996206" y="178.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-975C60B4-1A4D-44A7-BE55-741B4D345DE4" id="BPMNEdge_sid-975C60B4-1A4D-44A7-BE55-741B4D345DE4">
        <omgdi:waypoint x="240.0" y="234.4499967322892"/>
        <omgdi:waypoint x="240.0" y="295.0"/>
        <omgdi:waypoint x="509.9999999998038" y="295.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-B403E80B-732B-4B2F-B24E-8D6CCEB5D2BC" id="BPMNEdge_sid-B403E80B-732B-4B2F-B24E-8D6CCEB5D2BC">
        <omgdi:waypoint x="339.95000000000005" y="178.0"/>
        <omgdi:waypoint x="390.0" y="178.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-C1F8A328-9A8D-4151-8A49-9D6B418EC19E" id="BPMNEdge_sid-C1F8A328-9A8D-4151-8A49-9D6B418EC19E">
        <omgdi:waypoint x="429.94395820712947" y="178.0"/>
        <omgdi:waypoint x="524.9999999999847" y="178.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-363CB303-D81F-46F4-91C3-5A42A9A19C82" id="BPMNEdge_sid-363CB303-D81F-46F4-91C3-5A42A9A19C82">
        <omgdi:waypoint x="609.95" y="55.0"/>
        <omgdi:waypoint x="764.0" y="55.0"/>
        <omgdi:waypoint x="764.0" y="164.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-ABE88442-9760-4A48-94E7-8756F0E18D06" id="BPMNEdge_sid-ABE88442-9760-4A48-94E7-8756F0E18D06">
        <omgdi:waypoint x="624.9499999999675" y="178.0"/>
        <omgdi:waypoint x="750.0" y="178.0"/>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-1262DB2C-0B18-4D19-B604-BFB30977F8D8" id="BPMNEdge_sid-1262DB2C-0B18-4D19-B604-BFB30977F8D8">
        <omgdi:waypoint x="410.0" y="158.0"/>
        <omgdi:waypoint x="410.0" y="55.0"/>
        <omgdi:waypoint x="510.0" y="55.0"/>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```
