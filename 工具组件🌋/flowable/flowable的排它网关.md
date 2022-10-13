#### 使用排它网关测试

如果排它网关出来的流，没有设置条件的话，使用流程设计器的校验按钮去验证，会有提示。

不设置条件

![image](https://user-images.githubusercontent.com/97614802/195681562-7d6592ad-5421-45e0-ac36-7a4cc3e53a4b.png)

![image](https://user-images.githubusercontent.com/97614802/195681607-abb7752b-8fca-4753-b13a-f90826f8ec89.png)

当我设置一个 {outcome=='false'} {outcome=='true'} 就没有提示了。

如果不设置条件的话，那两个都是true，看别人说会取xml中第一条流为出口。那现在来试试

```xml
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:flowable="http://flowable.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.flowable.org/processdef">
  <process id="ceshi4" name="ceshi4" isExecutable="true">
    <startEvent id="startEvent1" flowable:formFieldValidation="true"></startEvent>
    <userTask id="sid-59EFBA33-631E-4B5D-80F9-8902B6D96037" flowable:assignee="6" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-AA771CAF-7944-4385-AE2F-8CFE19259453" sourceRef="startEvent1" targetRef="sid-59EFBA33-631E-4B5D-80F9-8902B6D96037"></sequenceFlow>
    <exclusiveGateway id="sid-60C3C58E-8461-488F-903C-AF9E55936CA4"></exclusiveGateway>
    <sequenceFlow id="sid-DB8AD602-A742-4161-A6ED-DE8B8D8F3EA1" sourceRef="sid-59EFBA33-631E-4B5D-80F9-8902B6D96037" targetRef="sid-60C3C58E-8461-488F-903C-AF9E55936CA4"></sequenceFlow>
    <userTask id="sid-E1C446F0-6C4E-4EEE-94F6-EF4BDB2F877C" flowable:assignee="7" flowable:formFieldValidation="true"></userTask>
    <userTask id="sid-5E6DABAC-7FEC-48BA-A15F-D4D2AB52943D" flowable:assignee="8" flowable:formFieldValidation="true"></userTask>
    <endEvent id="sid-86573A90-6A15-497D-ABD7-E272E488096E"></endEvent>
    <sequenceFlow id="sid-6D0F7ABE-024B-4B7F-843E-BA5A574F10BB" sourceRef="sid-E1C446F0-6C4E-4EEE-94F6-EF4BDB2F877C" targetRef="sid-86573A90-6A15-497D-ABD7-E272E488096E"></sequenceFlow>
    <sequenceFlow id="sid-9A6340BD-4D82-4DEC-A520-EEAD36B9CBDC" sourceRef="sid-5E6DABAC-7FEC-48BA-A15F-D4D2AB52943D" targetRef="sid-86573A90-6A15-497D-ABD7-E272E488096E"></sequenceFlow>
    <sequenceFlow id="sid-AAEF7F66-7A98-4D93-B506-FA514CD3CBC8" sourceRef="sid-60C3C58E-8461-488F-903C-AF9E55936CA4" targetRef="sid-E1C446F0-6C4E-4EEE-94F6-EF4BDB2F877C"></sequenceFlow>
    <sequenceFlow id="sid-5DB8242A-DC51-4DEB-9528-4B6447C2BBBF" sourceRef="sid-60C3C58E-8461-488F-903C-AF9E55936CA4" targetRef="sid-5E6DABAC-7FEC-48BA-A15F-D4D2AB52943D"></sequenceFlow>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_ceshi4">
    <bpmndi:BPMNPlane bpmnElement="ceshi4" id="BPMNPlane_ceshi4">
      <bpmndi:BPMNShape bpmnElement="startEvent1" id="BPMNShape_startEvent1">
        <omgdc:Bounds height="30.0" width="30.0" x="100.0" y="163.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-59EFBA33-631E-4B5D-80F9-8902B6D96037" id="BPMNShape_sid-59EFBA33-631E-4B5D-80F9-8902B6D96037">
        <omgdc:Bounds height="80.0" width="100.0" x="267.5" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-60C3C58E-8461-488F-903C-AF9E55936CA4" id="BPMNShape_sid-60C3C58E-8461-488F-903C-AF9E55936CA4">
        <omgdc:Bounds height="40.0" width="40.0" x="480.0" y="158.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-E1C446F0-6C4E-4EEE-94F6-EF4BDB2F877C" id="BPMNShape_sid-E1C446F0-6C4E-4EEE-94F6-EF4BDB2F877C">
        <omgdc:Bounds height="80.0" width="100.0" x="690.0" y="30.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-5E6DABAC-7FEC-48BA-A15F-D4D2AB52943D" id="BPMNShape_sid-5E6DABAC-7FEC-48BA-A15F-D4D2AB52943D">
        <omgdc:Bounds height="80.0" width="100.0" x="690.0" y="255.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-86573A90-6A15-497D-ABD7-E272E488096E" id="BPMNShape_sid-86573A90-6A15-497D-ABD7-E272E488096E">
        <omgdc:Bounds height="28.0" width="28.0" x="1057.5" y="144.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sid-6D0F7ABE-024B-4B7F-843E-BA5A574F10BB" id="BPMNEdge_sid-6D0F7ABE-024B-4B7F-843E-BA5A574F10BB">
        <omgdi:waypoint x="789.9499999999999" y="83.25972850678733"></omgdi:waypoint>
        <omgdi:waypoint x="1057.956646314636" y="154.4070834176793"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-9A6340BD-4D82-4DEC-A520-EEAD36B9CBDC" id="BPMNEdge_sid-9A6340BD-4D82-4DEC-A520-EEAD36B9CBDC">
        <omgdi:waypoint x="789.9499999999999" y="274.3363499245852"></omgdi:waypoint>
        <omgdi:waypoint x="1058.5579987989038" y="163.32947761174364"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-AAEF7F66-7A98-4D93-B506-FA514CD3CBC8" id="BPMNEdge_sid-AAEF7F66-7A98-4D93-B506-FA514CD3CBC8">
        <omgdi:waypoint x="514.2299568965517" y="172.26436781609195"></omgdi:waypoint>
        <omgdi:waypoint x="690.0" y="92.62870563674322"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-AA771CAF-7944-4385-AE2F-8CFE19259453" id="BPMNEdge_sid-AA771CAF-7944-4385-AE2F-8CFE19259453">
        <omgdi:waypoint x="129.94999955423248" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="267.5" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-5DB8242A-DC51-4DEB-9528-4B6447C2BBBF" id="BPMNEdge_sid-5DB8242A-DC51-4DEB-9528-4B6447C2BBBF">
        <omgdi:waypoint x="513.247746419545" y="184.70133426966288"></omgdi:waypoint>
        <omgdi:waypoint x="690.0" y="270.678496868476"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-DB8AD602-A742-4161-A6ED-DE8B8D8F3EA1" id="BPMNEdge_sid-DB8AD602-A742-4161-A6ED-DE8B8D8F3EA1">
        <omgdi:waypoint x="367.44999999992564" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="480.0" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```

xml中，到7是第一个

那么现在开启流程试下，确实跟猜想一样。

![image](https://user-images.githubusercontent.com/97614802/195688113-761c184a-7046-4e04-9c71-0af44af530e9.png)

那么说明排它网关只能走一个

但是呢，没有排它网关的话，去设置他会两个任务都会，会出现这种情况
