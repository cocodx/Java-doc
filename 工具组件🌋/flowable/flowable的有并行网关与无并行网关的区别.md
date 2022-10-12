#### 1.并行网关
##### 没有并行网关的文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:flowable="http://flowable.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.flowable.org/processdef">
  <process id="ceshikey" name="ceshi" isExecutable="true">
    <startEvent id="startEvent1" name="开始" flowable:formFieldValidation="true"></startEvent>
    <userTask id="sid-C0532635-D35F-469F-86CF-A48F82A7DC7D" name="审批1" flowable:assignee="6" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-947D2CDE-E6A5-47CF-A30D-32DCF375BC72" sourceRef="startEvent1" targetRef="sid-C0532635-D35F-469F-86CF-A48F82A7DC7D"></sequenceFlow>
    <userTask id="sid-ABEF8303-074D-425B-89DC-7970B4F2599E" name="审批3" flowable:assignee="7" flowable:formFieldValidation="true"></userTask>
    <userTask id="sid-C902F49F-153F-43C4-BF8F-2386F9EB9460" name="审批2" flowable:assignee="8" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-160D0921-3283-41AB-AB77-42EAC0E57F81" sourceRef="sid-C0532635-D35F-469F-86CF-A48F82A7DC7D" targetRef="sid-C902F49F-153F-43C4-BF8F-2386F9EB9460"></sequenceFlow>
    <sequenceFlow id="sid-1376FFE1-6EE7-444D-8859-68727DF85DCD" sourceRef="sid-C0532635-D35F-469F-86CF-A48F82A7DC7D" targetRef="sid-ABEF8303-074D-425B-89DC-7970B4F2599E"></sequenceFlow>
    <userTask id="sid-157D9EFD-8F90-42F3-A2A5-37FF571E6DF8" name="审批4" flowable:assignee="9" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-1FDB5DB7-402E-4844-95FE-4D7838175441" sourceRef="sid-ABEF8303-074D-425B-89DC-7970B4F2599E" targetRef="sid-157D9EFD-8F90-42F3-A2A5-37FF571E6DF8"></sequenceFlow>
    <sequenceFlow id="sid-A9A0A949-1360-46DE-AEDC-93BE92159E96" sourceRef="sid-C902F49F-153F-43C4-BF8F-2386F9EB9460" targetRef="sid-157D9EFD-8F90-42F3-A2A5-37FF571E6DF8"></sequenceFlow>
    <endEvent id="sid-9FB234E2-63E5-4B20-9AB8-0704357ECB78" name="结束"></endEvent>
    <sequenceFlow id="sid-588541C9-82BC-4D16-AEEE-184F70EC1D28" sourceRef="sid-157D9EFD-8F90-42F3-A2A5-37FF571E6DF8" targetRef="sid-9FB234E2-63E5-4B20-9AB8-0704357ECB78"></sequenceFlow>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_ceshikey">
    <bpmndi:BPMNPlane bpmnElement="ceshikey" id="BPMNPlane_ceshikey">
      <bpmndi:BPMNShape bpmnElement="startEvent1" id="BPMNShape_startEvent1">
        <omgdc:Bounds height="30.0" width="30.0" x="100.0" y="163.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-C0532635-D35F-469F-86CF-A48F82A7DC7D" id="BPMNShape_sid-C0532635-D35F-469F-86CF-A48F82A7DC7D">
        <omgdc:Bounds height="80.0" width="100.0" x="300.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-ABEF8303-074D-425B-89DC-7970B4F2599E" id="BPMNShape_sid-ABEF8303-074D-425B-89DC-7970B4F2599E">
        <omgdc:Bounds height="80.0" width="100.0" x="495.0" y="30.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-C902F49F-153F-43C4-BF8F-2386F9EB9460" id="BPMNShape_sid-C902F49F-153F-43C4-BF8F-2386F9EB9460">
        <omgdc:Bounds height="80.0" width="100.0" x="495.0" y="195.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-157D9EFD-8F90-42F3-A2A5-37FF571E6DF8" id="BPMNShape_sid-157D9EFD-8F90-42F3-A2A5-37FF571E6DF8">
        <omgdc:Bounds height="80.0" width="100.0" x="780.0" y="120.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-9FB234E2-63E5-4B20-9AB8-0704357ECB78" id="BPMNShape_sid-9FB234E2-63E5-4B20-9AB8-0704357ECB78">
        <omgdc:Bounds height="28.0" width="28.0" x="1035.0" y="146.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sid-A9A0A949-1360-46DE-AEDC-93BE92159E96" id="BPMNEdge_sid-A9A0A949-1360-46DE-AEDC-93BE92159E96">
        <omgdi:waypoint x="594.95" y="235.0"></omgdi:waypoint>
        <omgdi:waypoint x="830.0" y="235.0"></omgdi:waypoint>
        <omgdi:waypoint x="830.0" y="199.95"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-1FDB5DB7-402E-4844-95FE-4D7838175441" id="BPMNEdge_sid-1FDB5DB7-402E-4844-95FE-4D7838175441">
        <omgdi:waypoint x="594.949999999983" y="70.0"></omgdi:waypoint>
        <omgdi:waypoint x="830.0" y="70.0"></omgdi:waypoint>
        <omgdi:waypoint x="830.0" y="120.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-160D0921-3283-41AB-AB77-42EAC0E57F81" id="BPMNEdge_sid-160D0921-3283-41AB-AB77-42EAC0E57F81">
        <omgdi:waypoint x="350.0" y="217.95000000000002"></omgdi:waypoint>
        <omgdi:waypoint x="350.0" y="235.0"></omgdi:waypoint>
        <omgdi:waypoint x="495.0" y="235.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-1376FFE1-6EE7-444D-8859-68727DF85DCD" id="BPMNEdge_sid-1376FFE1-6EE7-444D-8859-68727DF85DCD">
        <omgdi:waypoint x="350.0" y="138.0"></omgdi:waypoint>
        <omgdi:waypoint x="350.0" y="70.0"></omgdi:waypoint>
        <omgdi:waypoint x="494.9999999999774" y="70.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-588541C9-82BC-4D16-AEEE-184F70EC1D28" id="BPMNEdge_sid-588541C9-82BC-4D16-AEEE-184F70EC1D28">
        <omgdi:waypoint x="879.9499999999925" y="160.0"></omgdi:waypoint>
        <omgdi:waypoint x="1035.0" y="160.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-947D2CDE-E6A5-47CF-A30D-32DCF375BC72" id="BPMNEdge_sid-947D2CDE-E6A5-47CF-A30D-32DCF375BC72">
        <omgdi:waypoint x="129.94999966898092" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="299.9999999999644" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```

结果正常运行，流转。
![image](https://user-images.githubusercontent.com/97614802/195268606-26d209de-52c6-4e6d-922f-be0aa7a5a948.png)

但是到了审批4的用户任务节点，流没有合并，他会有两个任务。一个是审批3的任务传过来的，一个是审批2的任务传过来的。正常来说，应该只有一条把。

![image](https://user-images.githubusercontent.com/97614802/195270865-0a275450-6e61-4127-b128-d8340a9f8c9e.png)
![image](https://user-images.githubusercontent.com/97614802/195271232-1fcde2a5-19f9-40f9-bf24-510e789a2153.png)


##### 有并行网关

```xml
<?xml version="1.0" encoding="UTF-8"?>
<definitions xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:flowable="http://flowable.org/bpmn" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC" xmlns:omgdi="http://www.omg.org/spec/DD/20100524/DI" typeLanguage="http://www.w3.org/2001/XMLSchema" expressionLanguage="http://www.w3.org/1999/XPath" targetNamespace="http://www.flowable.org/processdef">
  <process id="ceshi2key" name="ceshi2" isExecutable="true">
    <startEvent id="startEvent1" flowable:formFieldValidation="true"></startEvent>
    <userTask id="sid-B8870788-CE80-42D1-925D-33388BE18972" name="审批1" flowable:assignee="6" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-C647DC66-8C93-44B4-928F-3436A2ADD445" sourceRef="startEvent1" targetRef="sid-B8870788-CE80-42D1-925D-33388BE18972"></sequenceFlow>
    <parallelGateway id="sid-92B4050A-2A58-4485-B4DE-E2283E88646F"></parallelGateway>
    <sequenceFlow id="sid-4B00783B-AEDA-4F08-AF25-166E1E85A861" sourceRef="sid-B8870788-CE80-42D1-925D-33388BE18972" targetRef="sid-92B4050A-2A58-4485-B4DE-E2283E88646F"></sequenceFlow>
    <userTask id="sid-D9DFC1EC-FC36-480A-B695-B6DD51F0CAA0" name="审批3" flowable:assignee="7" flowable:formFieldValidation="true"></userTask>
    <userTask id="sid-69E6B713-3670-4224-9C60-0574CC676648" name="审批2" flowable:assignee="8" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-3A059D07-3AC1-4B5F-B0C2-A2FF15E5F1DB" sourceRef="sid-92B4050A-2A58-4485-B4DE-E2283E88646F" targetRef="sid-69E6B713-3670-4224-9C60-0574CC676648"></sequenceFlow>
    <sequenceFlow id="sid-CB73855A-59A0-4E9C-9258-E8439DF203EE" sourceRef="sid-92B4050A-2A58-4485-B4DE-E2283E88646F" targetRef="sid-D9DFC1EC-FC36-480A-B695-B6DD51F0CAA0"></sequenceFlow>
    <parallelGateway id="sid-1A59028C-50D9-4959-9F16-D161B7E837C7"></parallelGateway>
    <sequenceFlow id="sid-1A23DA21-8BD7-45DD-90E6-BEE775BA2D9B" sourceRef="sid-69E6B713-3670-4224-9C60-0574CC676648" targetRef="sid-1A59028C-50D9-4959-9F16-D161B7E837C7"></sequenceFlow>
    <sequenceFlow id="sid-8B950432-1B65-4458-885E-6021B6015DA4" sourceRef="sid-D9DFC1EC-FC36-480A-B695-B6DD51F0CAA0" targetRef="sid-1A59028C-50D9-4959-9F16-D161B7E837C7"></sequenceFlow>
    <userTask id="sid-DB8B3552-DDDC-4F9D-82AC-A4C8042E6E10" name="审批4" flowable:assignee="9" flowable:formFieldValidation="true"></userTask>
    <sequenceFlow id="sid-F9DB6A3C-63CF-4F21-A1A1-2451342D1190" sourceRef="sid-1A59028C-50D9-4959-9F16-D161B7E837C7" targetRef="sid-DB8B3552-DDDC-4F9D-82AC-A4C8042E6E10"></sequenceFlow>
    <endEvent id="sid-E678DA13-445F-4948-B62B-4AA9E853F944"></endEvent>
    <sequenceFlow id="sid-68DF5D1A-B087-4777-BC40-DD8F0A583CAA" sourceRef="sid-DB8B3552-DDDC-4F9D-82AC-A4C8042E6E10" targetRef="sid-E678DA13-445F-4948-B62B-4AA9E853F944"></sequenceFlow>
  </process>
  <bpmndi:BPMNDiagram id="BPMNDiagram_ceshi2key">
    <bpmndi:BPMNPlane bpmnElement="ceshi2key" id="BPMNPlane_ceshi2key">
      <bpmndi:BPMNShape bpmnElement="startEvent1" id="BPMNShape_startEvent1">
        <omgdc:Bounds height="30.0" width="30.0" x="100.0" y="163.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-B8870788-CE80-42D1-925D-33388BE18972" id="BPMNShape_sid-B8870788-CE80-42D1-925D-33388BE18972">
        <omgdc:Bounds height="80.0" width="100.0" x="255.0" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-92B4050A-2A58-4485-B4DE-E2283E88646F" id="BPMNShape_sid-92B4050A-2A58-4485-B4DE-E2283E88646F">
        <omgdc:Bounds height="40.0" width="40.0" x="450.0" y="158.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-D9DFC1EC-FC36-480A-B695-B6DD51F0CAA0" id="BPMNShape_sid-D9DFC1EC-FC36-480A-B695-B6DD51F0CAA0">
        <omgdc:Bounds height="80.0" width="100.0" x="581.5" y="60.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-69E6B713-3670-4224-9C60-0574CC676648" id="BPMNShape_sid-69E6B713-3670-4224-9C60-0574CC676648">
        <omgdc:Bounds height="80.0" width="100.0" x="581.5" y="240.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-1A59028C-50D9-4959-9F16-D161B7E837C7" id="BPMNShape_sid-1A59028C-50D9-4959-9F16-D161B7E837C7">
        <omgdc:Bounds height="40.0" width="40.0" x="804.5" y="158.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-DB8B3552-DDDC-4F9D-82AC-A4C8042E6E10" id="BPMNShape_sid-DB8B3552-DDDC-4F9D-82AC-A4C8042E6E10">
        <omgdc:Bounds height="80.0" width="100.0" x="889.5" y="138.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNShape bpmnElement="sid-E678DA13-445F-4948-B62B-4AA9E853F944" id="BPMNShape_sid-E678DA13-445F-4948-B62B-4AA9E853F944">
        <omgdc:Bounds height="28.0" width="28.0" x="1092.5" y="164.0"></omgdc:Bounds>
      </bpmndi:BPMNShape>
      <bpmndi:BPMNEdge bpmnElement="sid-CB73855A-59A0-4E9C-9258-E8439DF203EE" id="BPMNEdge_sid-CB73855A-59A0-4E9C-9258-E8439DF203EE">
        <omgdi:waypoint x="470.5" y="158.5"></omgdi:waypoint>
        <omgdi:waypoint x="470.5" y="100.0"></omgdi:waypoint>
        <omgdi:waypoint x="581.5" y="100.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-F9DB6A3C-63CF-4F21-A1A1-2451342D1190" id="BPMNEdge_sid-F9DB6A3C-63CF-4F21-A1A1-2451342D1190">
        <omgdi:waypoint x="844.0247370727428" y="178.41666666666663"></omgdi:waypoint>
        <omgdi:waypoint x="889.4999999999953" y="178.21812227074233"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-8B950432-1B65-4458-885E-6021B6015DA4" id="BPMNEdge_sid-8B950432-1B65-4458-885E-6021B6015DA4">
        <omgdi:waypoint x="681.4499999999999" y="100.0"></omgdi:waypoint>
        <omgdi:waypoint x="824.5" y="100.0"></omgdi:waypoint>
        <omgdi:waypoint x="824.5" y="158.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-68DF5D1A-B087-4777-BC40-DD8F0A583CAA" id="BPMNEdge_sid-68DF5D1A-B087-4777-BC40-DD8F0A583CAA">
        <omgdi:waypoint x="989.4499999999143" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="1092.5" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-1A23DA21-8BD7-45DD-90E6-BEE775BA2D9B" id="BPMNEdge_sid-1A23DA21-8BD7-45DD-90E6-BEE775BA2D9B">
        <omgdi:waypoint x="681.44999999996" y="280.0"></omgdi:waypoint>
        <omgdi:waypoint x="824.5" y="280.0"></omgdi:waypoint>
        <omgdi:waypoint x="824.5" y="197.90973994111877"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-4B00783B-AEDA-4F08-AF25-166E1E85A861" id="BPMNEdge_sid-4B00783B-AEDA-4F08-AF25-166E1E85A861">
        <omgdi:waypoint x="354.9499999999934" y="178.15090634441086"></omgdi:waypoint>
        <omgdi:waypoint x="450.439393939384" y="178.4393939393939"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-C647DC66-8C93-44B4-928F-3436A2ADD445" id="BPMNEdge_sid-C647DC66-8C93-44B4-928F-3436A2ADD445">
        <omgdi:waypoint x="129.94999949366624" y="178.0"></omgdi:waypoint>
        <omgdi:waypoint x="254.99999999993574" y="178.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
      <bpmndi:BPMNEdge bpmnElement="sid-3A059D07-3AC1-4B5F-B0C2-A2FF15E5F1DB" id="BPMNEdge_sid-3A059D07-3AC1-4B5F-B0C2-A2FF15E5F1DB">
        <omgdi:waypoint x="470.5" y="197.44067421259845"></omgdi:waypoint>
        <omgdi:waypoint x="470.5" y="280.0"></omgdi:waypoint>
        <omgdi:waypoint x="581.5" y="280.0"></omgdi:waypoint>
      </bpmndi:BPMNEdge>
    </bpmndi:BPMNPlane>
  </bpmndi:BPMNDiagram>
</definitions>
```
![image](https://user-images.githubusercontent.com/97614802/195273761-eab4b058-b506-453b-81d3-9a18b9d6e569.png)

当跑到审批4的节点时候，只有一个任务，合并流了。

![image](https://user-images.githubusercontent.com/97614802/195273941-b4b69122-aef2-4fad-84ab-6c32e5a6c0e3.png)

