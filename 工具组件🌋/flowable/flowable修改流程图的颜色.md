#### flowable修改流程图的颜色

背景介绍：

把实现的部门的箭头颜色改成绿色，未完成的颜色弄成红色。

代码如下：

FlowProcessDiagramGenerator1111 类

```java

import java.io.InputStream;
import java.util.List;

import org.flowable.bpmn.model.BpmnModel;
import org.springframework.stereotype.Service;

@Service
public class FlowProcessDiagramGenerator1111 extends MyDefaultProcessDiagramGenerator{

    private static final String IMAGE_TYPE = "png";

    /**
     * 生成图片流
     *
     * @param bpmnModel             模型
     * @param highLightedActivities 活动节点
     * @param highLightedFlows      高亮线
     * @return
     */
    public InputStream generateDiagram(BpmnModel bpmnModel, List<String> highLightedActivities, List<String> highLightedFlows) {
        return generateDiagram(bpmnModel, IMAGE_TYPE, highLightedActivities,
                highLightedFlows, "宋体", "宋体", "宋体",
                null, 1.0, true);
    }
}
```

MyDefaultProcessDiagramCanvas1111 类

```java
package com.yamcanda.icube.bpm.config;

import org.flowable.bpmn.model.AssociationDirection;
import org.flowable.image.impl.DefaultProcessDiagramCanvas;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

public class MyDefaultProcessDiagramCanvas1111 extends DefaultProcessDiagramCanvas {

    protected static Color HIGHLIGHT_SEQUENCE_FLOW_COLOR = Color.GREEN;

    public MyDefaultProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        super(width, height, minX, minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
    }

    public MyDefaultProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType) {
        super(width, height, minX, minY, imageType);
    }

    /**
     * 画线颜色设置
     * @param xPoints
     * @param yPoints
     * @param conditional
     * @param isDefault
     * @param connectionType
     * @param associationDirection
     * @param highLighted
     * @param scaleFactor
     */
    public void drawConnection(int[] xPoints, int[] yPoints, boolean conditional, boolean isDefault, String connectionType, AssociationDirection associationDirection,
                               boolean highLighted,double scaleFactor){
        Paint originalPaint = g.getPaint();
        Stroke originalStroke = g.getStroke();

        g.setPaint(CONNECTION_COLOR);
        if (connectionType.equals("association")){
            g.setStroke(ASSOCIATION_STROKE);
        }else if (highLighted){
            //设置线的颜色
            g.setPaint(HIGHLIGHT_SEQUENCE_FLOW_COLOR);
            g.setStroke(HIGHLIGHT_FLOW_STROKE);
        }

        for (int i=1;i< xPoints.length;i++){
            Integer sourceX = xPoints[i-1];
            Integer sourceY = yPoints[i-1];
            Integer targetX = xPoints[i];
            Integer targetY = yPoints[i];
            Line2D.Double line = new Line2D.Double(sourceX,sourceY,targetX,targetY);
            g.draw(line);
        }

        if (isDefault){
            Line2D.Double line = new Line2D.Double(xPoints[0],yPoints[0],xPoints[1],yPoints[1]);
            drawDefaultSequenceFlowIndicator(line,scaleFactor);
        }

        if (conditional){
            Line2D.Double line = new Line2D.Double(xPoints[0],yPoints[0],xPoints[1],yPoints[1]);
            drawConditionalSequenceFlowIndicator(line,scaleFactor);
        }

        if (associationDirection== AssociationDirection.ONE || associationDirection== AssociationDirection.BOTH){
            Line2D.Double line = new Line2D.Double(xPoints[xPoints.length-2],yPoints[yPoints.length-2],xPoints[xPoints.length-1],yPoints[yPoints.length-1]);
            drawArrowHead(line,scaleFactor);
        }

        if (associationDirection == AssociationDirection.BOTH){
            Line2D.Double line = new Line2D.Double(xPoints[1],yPoints[1],xPoints[0],yPoints[0]);
            drawArrowHead(line,scaleFactor);
        }
        g.setPaint(originalPaint);
        g.setStroke(originalStroke);
    }

    /**
     * 高亮节点设置
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public void drawHighLight(int x,int y,int width,int height){
        Paint originalPaint = g.getPaint();
        Stroke originalStroke = g.getStroke();
        //设置高亮节点的颜色
        g.setPaint(HIGHLIGHT_COLOR);
        g.setStroke(THICK_TASK_BORDER_STROKE);

        RoundRectangle2D rect = new RoundRectangle2D.Double(x,y,width,height,20,20);
        g.draw(rect);

        g.setPaint(originalPaint);
        g.setStroke(originalStroke);
    }


}

```

MyDefaultProcessDiagramGenerator1111 类

```java
package com.yamcanda.icube.bpm.config;

import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.image.ProcessDiagramGenerator;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;

public class MyDefaultProcessDiagramGenerator1111 implements ProcessDiagramGenerator {

    protected Map<Class<? extends BaseElement>, MyDefaultProcessDiagramGenerator.ActivityDrawInstruction> activityDrawInstructions;
    protected Map<Class<? extends BaseElement>, MyDefaultProcessDiagramGenerator.ArtifactDrawInstruction> artifactDrawInstructions;

    public MyDefaultProcessDiagramGenerator() {
        this(1.0);
    }

    public MyDefaultProcessDiagramGenerator(final double scaleFactor) {
        this.activityDrawInstructions = new HashMap();
        this.artifactDrawInstructions = new HashMap();
        this.activityDrawInstructions.put(StartEvent.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                StartEvent startEvent = (StartEvent)flowNode;
                if (startEvent.getEventDefinitions() != null && !startEvent.getEventDefinitions().isEmpty()) {
                    EventDefinition eventDefinition = (EventDefinition)startEvent.getEventDefinitions().get(0);
                    if (eventDefinition instanceof TimerEventDefinition) {
                        processDiagramCanvas.drawTimerStartEvent(graphicInfo, scaleFactor);
                    } else if (eventDefinition instanceof ErrorEventDefinition) {
                        processDiagramCanvas.drawErrorStartEvent(graphicInfo, scaleFactor);
                    } else if (eventDefinition instanceof EscalationEventDefinition) {
                        processDiagramCanvas.drawEscalationStartEvent(graphicInfo, scaleFactor);
                    } else if (eventDefinition instanceof ConditionalEventDefinition) {
                        processDiagramCanvas.drawConditionalStartEvent(graphicInfo, scaleFactor);
                    } else if (eventDefinition instanceof SignalEventDefinition) {
                        processDiagramCanvas.drawSignalStartEvent(graphicInfo, scaleFactor);
                    } else if (eventDefinition instanceof MessageEventDefinition) {
                        processDiagramCanvas.drawMessageStartEvent(graphicInfo, scaleFactor);
                    } else {
                        processDiagramCanvas.drawNoneStartEvent(graphicInfo);
                    }
                } else {
                    List<ExtensionElement> eventTypeElements = (List)startEvent.getExtensionElements().get("eventType");
                    if (eventTypeElements != null && eventTypeElements.size() > 0) {
                        processDiagramCanvas.drawEventRegistryStartEvent(graphicInfo, scaleFactor);
                    } else {
                        processDiagramCanvas.drawNoneStartEvent(graphicInfo);
                    }
                }

            }
        });
        this.activityDrawInstructions.put(IntermediateCatchEvent.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                IntermediateCatchEvent intermediateCatchEvent = (IntermediateCatchEvent)flowNode;
                if (intermediateCatchEvent.getEventDefinitions() != null && !intermediateCatchEvent.getEventDefinitions().isEmpty()) {
                    if (intermediateCatchEvent.getEventDefinitions().get(0) instanceof SignalEventDefinition) {
                        processDiagramCanvas.drawCatchingSignalEvent(flowNode.getName(), graphicInfo, true, scaleFactor);
                    } else if (intermediateCatchEvent.getEventDefinitions().get(0) instanceof TimerEventDefinition) {
                        processDiagramCanvas.drawCatchingTimerEvent(flowNode.getName(), graphicInfo, true, scaleFactor);
                    } else if (intermediateCatchEvent.getEventDefinitions().get(0) instanceof MessageEventDefinition) {
                        processDiagramCanvas.drawCatchingMessageEvent(flowNode.getName(), graphicInfo, true, scaleFactor);
                    } else if (intermediateCatchEvent.getEventDefinitions().get(0) instanceof ConditionalEventDefinition) {
                        processDiagramCanvas.drawCatchingConditionalEvent(flowNode.getName(), graphicInfo, true, scaleFactor);
                    }
                }

            }
        });
        this.activityDrawInstructions.put(ThrowEvent.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                ThrowEvent throwEvent = (ThrowEvent)flowNode;
                if (throwEvent.getEventDefinitions() != null && !throwEvent.getEventDefinitions().isEmpty()) {
                    if (throwEvent.getEventDefinitions().get(0) instanceof SignalEventDefinition) {
                        processDiagramCanvas.drawThrowingSignalEvent(graphicInfo, scaleFactor);
                    } else if (throwEvent.getEventDefinitions().get(0) instanceof EscalationEventDefinition) {
                        processDiagramCanvas.drawThrowingEscalationEvent(graphicInfo, scaleFactor);
                    } else if (throwEvent.getEventDefinitions().get(0) instanceof CompensateEventDefinition) {
                        processDiagramCanvas.drawThrowingCompensateEvent(graphicInfo, scaleFactor);
                    } else {
                        processDiagramCanvas.drawThrowingNoneEvent(graphicInfo, scaleFactor);
                    }
                } else {
                    processDiagramCanvas.drawThrowingNoneEvent(graphicInfo, scaleFactor);
                }

            }
        });
        this.activityDrawInstructions.put(EndEvent.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                EndEvent endEvent = (EndEvent)flowNode;
                if (endEvent.getEventDefinitions() != null && !endEvent.getEventDefinitions().isEmpty()) {
                    if (endEvent.getEventDefinitions().get(0) instanceof ErrorEventDefinition) {
                        processDiagramCanvas.drawErrorEndEvent(flowNode.getName(), graphicInfo, scaleFactor);
                    } else if (endEvent.getEventDefinitions().get(0) instanceof EscalationEventDefinition) {
                        processDiagramCanvas.drawEscalationEndEvent(flowNode.getName(), graphicInfo, scaleFactor);
                    } else {
                        processDiagramCanvas.drawNoneEndEvent(graphicInfo, scaleFactor);
                    }
                } else {
                    processDiagramCanvas.drawNoneEndEvent(graphicInfo, scaleFactor);
                }

            }
        });
        this.activityDrawInstructions.put(Task.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(UserTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawUserTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(ScriptTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawScriptTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(ServiceTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                ServiceTask serviceTask = (ServiceTask)flowNode;
                if ("camel".equalsIgnoreCase(serviceTask.getType())) {
                    processDiagramCanvas.drawCamelTask(serviceTask.getName(), graphicInfo, scaleFactor);
                } else if ("mule".equalsIgnoreCase(serviceTask.getType())) {
                    processDiagramCanvas.drawMuleTask(serviceTask.getName(), graphicInfo, scaleFactor);
                } else if ("http".equalsIgnoreCase(serviceTask.getType())) {
                    processDiagramCanvas.drawHttpTask(serviceTask.getName(), graphicInfo, scaleFactor);
                } else if ("dmn".equalsIgnoreCase(serviceTask.getType())) {
                    processDiagramCanvas.drawDMNTask(serviceTask.getName(), graphicInfo, scaleFactor);
                } else if ("shell".equalsIgnoreCase(serviceTask.getType())) {
                    processDiagramCanvas.drawShellTask(serviceTask.getName(), graphicInfo, scaleFactor);
                } else {
                    processDiagramCanvas.drawServiceTask(serviceTask.getName(), graphicInfo, scaleFactor);
                }

            }
        });
        this.activityDrawInstructions.put(HttpServiceTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawHttpTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(ReceiveTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawReceiveTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(SendTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawSendTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(ManualTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawManualTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(SendEventServiceTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawSendEventServiceTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(ExternalWorkerServiceTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                ServiceTask serviceTask = (ServiceTask)flowNode;
                processDiagramCanvas.drawServiceTask(serviceTask.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(CaseServiceTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawCaseServiceTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(BusinessRuleTask.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawBusinessRuleTask(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(ExclusiveGateway.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawExclusiveGateway(graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(InclusiveGateway.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawInclusiveGateway(graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(ParallelGateway.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawParallelGateway(graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(EventGateway.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawEventBasedGateway(graphicInfo, scaleFactor);
            }
        });
        this.activityDrawInstructions.put(BoundaryEvent.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                BoundaryEvent boundaryEvent = (BoundaryEvent)flowNode;
                if (boundaryEvent.getEventDefinitions() != null && !boundaryEvent.getEventDefinitions().isEmpty()) {
                    EventDefinition eventDefinition = (EventDefinition)boundaryEvent.getEventDefinitions().get(0);
                    if (eventDefinition instanceof TimerEventDefinition) {
                        processDiagramCanvas.drawCatchingTimerEvent(flowNode.getName(), graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    } else if (eventDefinition instanceof ConditionalEventDefinition) {
                        processDiagramCanvas.drawCatchingConditionalEvent(graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    } else if (eventDefinition instanceof ErrorEventDefinition) {
                        processDiagramCanvas.drawCatchingErrorEvent(graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    } else if (eventDefinition instanceof EscalationEventDefinition) {
                        processDiagramCanvas.drawCatchingEscalationEvent(graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    } else if (eventDefinition instanceof SignalEventDefinition) {
                        processDiagramCanvas.drawCatchingSignalEvent(flowNode.getName(), graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    } else if (eventDefinition instanceof MessageEventDefinition) {
                        processDiagramCanvas.drawCatchingMessageEvent(flowNode.getName(), graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    } else if (eventDefinition instanceof CompensateEventDefinition) {
                        processDiagramCanvas.drawCatchingCompensateEvent(graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    }
                } else {
                    List<ExtensionElement> eventTypeElements = (List)boundaryEvent.getExtensionElements().get("eventType");
                    if (eventTypeElements != null && eventTypeElements.size() > 0) {
                        processDiagramCanvas.drawCatchingEventRegistryEvent(flowNode.getName(), graphicInfo, boundaryEvent.isCancelActivity(), scaleFactor);
                    }
                }

            }
        });
        this.activityDrawInstructions.put(SubProcess.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                if (graphicInfo.getExpanded() != null && !graphicInfo.getExpanded()) {
                    processDiagramCanvas.drawCollapsedSubProcess(flowNode.getName(), graphicInfo, false, scaleFactor);
                } else {
                    processDiagramCanvas.drawExpandedSubProcess(flowNode.getName(), graphicInfo, false, scaleFactor);
                }

            }
        });
        this.activityDrawInstructions.put(Transaction.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                if (graphicInfo.getExpanded() != null && !graphicInfo.getExpanded()) {
                    processDiagramCanvas.drawCollapsedSubProcess(flowNode.getName(), graphicInfo, false, scaleFactor);
                } else {
                    processDiagramCanvas.drawExpandedTransaction(flowNode.getName(), graphicInfo, scaleFactor);
                }

            }
        });
        this.activityDrawInstructions.put(EventSubProcess.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                if (graphicInfo.getExpanded() != null && !graphicInfo.getExpanded()) {
                    processDiagramCanvas.drawCollapsedSubProcess(flowNode.getName(), graphicInfo, true, scaleFactor);
                } else {
                    processDiagramCanvas.drawExpandedSubProcess(flowNode.getName(), graphicInfo, true, scaleFactor);
                }

            }
        });
        this.activityDrawInstructions.put(AdhocSubProcess.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                if (graphicInfo.getExpanded() != null && !graphicInfo.getExpanded()) {
                    processDiagramCanvas.drawCollapsedSubProcess(flowNode.getName(), graphicInfo, false, scaleFactor);
                } else {
                    processDiagramCanvas.drawExpandedSubProcess(flowNode.getName(), graphicInfo, false, scaleFactor);
                }

            }
        });
        this.activityDrawInstructions.put(CallActivity.class, new MyDefaultProcessDiagramGenerator.ActivityDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
                processDiagramCanvas.drawCollapsedCallActivity(flowNode.getName(), graphicInfo, scaleFactor);
            }
        });
        this.artifactDrawInstructions.put(TextAnnotation.class, new MyDefaultProcessDiagramGenerator.ArtifactDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, Artifact artifact) {
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(artifact.getId());
                TextAnnotation textAnnotation = (TextAnnotation)artifact;
                processDiagramCanvas.drawTextAnnotation(textAnnotation.getText(), graphicInfo, scaleFactor);
            }
        });
        this.artifactDrawInstructions.put(Association.class, new MyDefaultProcessDiagramGenerator.ArtifactDrawInstruction() {
            public void draw(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, Artifact artifact) {
                Association association = (Association)artifact;
                String sourceRef = association.getSourceRef();
                String targetRef = association.getTargetRef();
                BaseElement sourceElement = bpmnModel.getFlowElement(sourceRef);
                BaseElement targetElement = bpmnModel.getFlowElement(targetRef);
                if (sourceElement == null) {
                    sourceElement = bpmnModel.getArtifact(sourceRef);
                }

                if (targetElement == null) {
                    targetElement = bpmnModel.getArtifact(targetRef);
                }

                List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(artifact.getId());
                graphicInfoList = MyDefaultProcessDiagramGenerator.connectionPerfectionizer(processDiagramCanvas, bpmnModel, (BaseElement)sourceElement, (BaseElement)targetElement, graphicInfoList);
                int[] xPoints = new int[graphicInfoList.size()];
                int[] yPoints = new int[graphicInfoList.size()];

                for(int i = 1; i < graphicInfoList.size(); ++i) {
                    GraphicInfo graphicInfo = (GraphicInfo)graphicInfoList.get(i);
                    GraphicInfo previousGraphicInfo = (GraphicInfo)graphicInfoList.get(i - 1);
                    if (i == 1) {
                        xPoints[0] = (int)previousGraphicInfo.getX();
                        yPoints[0] = (int)previousGraphicInfo.getY();
                    }

                    xPoints[i] = (int)graphicInfo.getX();
                    yPoints[i] = (int)graphicInfo.getY();
                }

                AssociationDirection associationDirection = association.getAssociationDirection();
                processDiagramCanvas.drawAssociation(xPoints, yPoints, associationDirection, false, scaleFactor);
            }
        });
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, List<String> highLightedFlows, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateProcessDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows, activityFontName, labelFontName, annotationFontName, customClassLoader, scaleFactor, drawSequenceFlowNameWithNoLabelDI).generateImage(imageType);
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, List<String> highLightedFlows, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows, (String)null, (String)null, (String)null, (ClassLoader)null, 1.0, drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, List<String> highLightedFlows, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows, (String)null, (String)null, (String)null, (ClassLoader)null, scaleFactor, drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, imageType, highLightedActivities, Collections.emptyList(), drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, imageType, highLightedActivities, Collections.emptyList(), scaleFactor, drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, imageType, Collections.emptyList(), Collections.emptyList(), activityFontName, labelFontName, annotationFontName, customClassLoader, 1.0, drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, imageType, Collections.emptyList(), Collections.emptyList(), activityFontName, labelFontName, annotationFontName, customClassLoader, scaleFactor, drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generatePngDiagram(BpmnModel bpmnModel, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generatePngDiagram(bpmnModel, 1.0, drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generatePngDiagram(BpmnModel bpmnModel, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, "png", Collections.emptyList(), Collections.emptyList(), scaleFactor, drawSequenceFlowNameWithNoLabelDI);
    }

    public InputStream generateJpgDiagram(BpmnModel bpmnModel) {
        return this.generateJpgDiagram(bpmnModel, 1.0, false);
    }

    public InputStream generateJpgDiagram(BpmnModel bpmnModel, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateDiagram(bpmnModel, "jpg", Collections.emptyList(), Collections.emptyList(), drawSequenceFlowNameWithNoLabelDI);
    }

    public BufferedImage generateImage(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, List<String> highLightedFlows, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateProcessDiagram(bpmnModel, imageType, highLightedActivities, highLightedFlows, activityFontName, labelFontName, annotationFontName, customClassLoader, scaleFactor, drawSequenceFlowNameWithNoLabelDI).generateBufferedImage(imageType);
    }

    public BufferedImage generateImage(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, List<String> highLightedFlows, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        return this.generateImage(bpmnModel, imageType, highLightedActivities, highLightedFlows, (String)null, (String)null, (String)null, (ClassLoader)null, scaleFactor, drawSequenceFlowNameWithNoLabelDI);
    }

    public BufferedImage generatePngImage(BpmnModel bpmnModel, double scaleFactor) {
        return this.generateImage(bpmnModel, "png", Collections.emptyList(), Collections.emptyList(), scaleFactor, false);
    }

    protected MyDefaultProcessDiagramCanvas generateProcessDiagram(BpmnModel bpmnModel, String imageType, List<String> highLightedActivities, List<String> highLightedFlows, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader, double scaleFactor, boolean drawSequenceFlowNameWithNoLabelDI) {
        this.prepareBpmnModel(bpmnModel);
        MyDefaultProcessDiagramCanvas processDiagramCanvas = initProcessDiagramCanvas(bpmnModel, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
        Iterator var13 = bpmnModel.getPools().iterator();

        while(var13.hasNext()) {
            Pool pool = (Pool)var13.next();
            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
            processDiagramCanvas.drawPoolOrLane(pool.getName(), graphicInfo, scaleFactor);
        }

        var13 = bpmnModel.getProcesses().iterator();

        org.flowable.bpmn.model.Process process;
        Iterator var22;
        while(var13.hasNext()) {
            process = (org.flowable.bpmn.model.Process)var13.next();
            var22 = process.getLanes().iterator();

            while(var22.hasNext()) {
                Lane lane = (Lane)var22.next();
                GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(lane.getId());
                processDiagramCanvas.drawPoolOrLane(lane.getName(), graphicInfo, scaleFactor);
            }
        }

        var13 = bpmnModel.getProcesses().iterator();

        while(var13.hasNext()) {
            process = (org.flowable.bpmn.model.Process)var13.next();
            var22 = process.findFlowElementsOfType(FlowNode.class).iterator();

            while(var22.hasNext()) {
                FlowNode flowNode = (FlowNode)var22.next();
                if (!this.isPartOfCollapsedSubProcess(flowNode, bpmnModel)) {
                    this.drawActivity(processDiagramCanvas, bpmnModel, flowNode, highLightedActivities, highLightedFlows, scaleFactor, drawSequenceFlowNameWithNoLabelDI);
                }
            }
        }

        var13 = bpmnModel.getProcesses().iterator();

        label75:
        while(true) {
            List subProcesses;
            do {
                if (!var13.hasNext()) {
                    return processDiagramCanvas;
                }

                process = (org.flowable.bpmn.model.Process)var13.next();
                var22 = process.getArtifacts().iterator();

                while(var22.hasNext()) {
                    Artifact artifact = (Artifact)var22.next();
                    this.drawArtifact(processDiagramCanvas, bpmnModel, artifact);
                }

                subProcesses = process.findFlowElementsOfType(SubProcess.class, true);
            } while(subProcesses == null);

            Iterator var26 = subProcesses.iterator();

            while(true) {
                GraphicInfo graphicInfo;
                SubProcess subProcess;
                do {
                    do {
                        if (!var26.hasNext()) {
                            continue label75;
                        }

                        subProcess = (SubProcess)var26.next();
                        graphicInfo = bpmnModel.getGraphicInfo(subProcess.getId());
                    } while(graphicInfo != null && graphicInfo.getExpanded() != null && !graphicInfo.getExpanded());
                } while(this.isPartOfCollapsedSubProcess(subProcess, bpmnModel));

                Iterator var19 = subProcess.getArtifacts().iterator();

                while(var19.hasNext()) {
                    Artifact subProcessArtifact = (Artifact)var19.next();
                    this.drawArtifact(processDiagramCanvas, bpmnModel, subProcessArtifact);
                }
            }
        }
    }

    protected void prepareBpmnModel(BpmnModel bpmnModel) {
        List<GraphicInfo> allGraphicInfos = new ArrayList();
        if (bpmnModel.getLocationMap() != null) {
            allGraphicInfos.addAll(bpmnModel.getLocationMap().values());
        }

        if (bpmnModel.getLabelLocationMap() != null) {
            allGraphicInfos.addAll(bpmnModel.getLabelLocationMap().values());
        }

        if (bpmnModel.getFlowLocationMap() != null) {
            Iterator var3 = bpmnModel.getFlowLocationMap().values().iterator();

            while(var3.hasNext()) {
                List<GraphicInfo> flowGraphicInfos = (List)var3.next();
                allGraphicInfos.addAll(flowGraphicInfos);
            }
        }

        if (allGraphicInfos.size() > 0) {
            boolean needsTranslationX = false;
            boolean needsTranslationY = false;
            double lowestX = 0.0;
            double lowestY = 0.0;
            Iterator var9 = allGraphicInfos.iterator();

            double translationY;
            while(var9.hasNext()) {
                GraphicInfo graphicInfo = (GraphicInfo)var9.next();
                translationY = graphicInfo.getX();
                double y = graphicInfo.getY();
                if (translationY < lowestX) {
                    needsTranslationX = true;
                    lowestX = translationY;
                }

                if (y < lowestY) {
                    needsTranslationY = true;
                    lowestY = y;
                }
            }

            if (needsTranslationX || needsTranslationY) {
                double translationX = Math.abs(lowestX);
                translationY = Math.abs(lowestY);
                Iterator var18 = allGraphicInfos.iterator();

                while(var18.hasNext()) {
                    GraphicInfo graphicInfo = (GraphicInfo)var18.next();
                    if (needsTranslationX) {
                        graphicInfo.setX(graphicInfo.getX() + translationX);
                    }

                    if (needsTranslationY) {
                        graphicInfo.setY(graphicInfo.getY() + translationY);
                    }
                }
            }
        }

    }

    protected void drawActivity(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode, List<String> highLightedActivities, List<String> highLightedFlows, double scaleFactor, Boolean drawSequenceFlowNameWithNoLabelDI) {
        MyDefaultProcessDiagramGenerator.ActivityDrawInstruction drawInstruction = (MyDefaultProcessDiagramGenerator.ActivityDrawInstruction)this.activityDrawInstructions.get(flowNode.getClass());
        boolean highLighted;
        if (drawInstruction != null) {
            drawInstruction.draw(processDiagramCanvas, bpmnModel, flowNode);
            boolean multiInstanceSequential = false;
            boolean multiInstanceParallel = false;
            highLighted = false;
            if (flowNode instanceof Activity) {
                Activity activity = (Activity)flowNode;
                MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = activity.getLoopCharacteristics();
                if (multiInstanceLoopCharacteristics != null) {
                    multiInstanceSequential = multiInstanceLoopCharacteristics.isSequential();
                    multiInstanceParallel = !multiInstanceSequential;
                }
            }

            GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
            if (!(flowNode instanceof SubProcess)) {
                if (flowNode instanceof CallActivity) {
                    highLighted = true;
                }
            } else {
                highLighted = graphicInfo.getExpanded() != null && !graphicInfo.getExpanded();
            }

            if (scaleFactor == 1.0) {
                processDiagramCanvas.drawActivityMarkers((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight(), multiInstanceSequential, multiInstanceParallel, highLighted);
            }

            if (highLightedActivities.contains(flowNode.getId())) {
                drawHighLight(processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()));
            }
        } else if (flowNode instanceof Task) {
            ((MyDefaultProcessDiagramGenerator.ActivityDrawInstruction)this.activityDrawInstructions.get(Task.class)).draw(processDiagramCanvas, bpmnModel, flowNode);
            if (highLightedActivities.contains(flowNode.getId())) {
                drawHighLight(processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()));
            }
        }

        Iterator var26 = flowNode.getOutgoingFlows().iterator();

        while(var26.hasNext()) {
            SequenceFlow sequenceFlow = (SequenceFlow)var26.next();
            highLighted = highLightedFlows.contains(sequenceFlow.getId());
            String defaultFlow = null;
            if (flowNode instanceof Activity) {
                defaultFlow = ((Activity)flowNode).getDefaultFlow();
            } else if (flowNode instanceof Gateway) {
                defaultFlow = ((Gateway)flowNode).getDefaultFlow();
            }

            boolean isDefault = false;
            if (defaultFlow != null && defaultFlow.equalsIgnoreCase(sequenceFlow.getId())) {
                isDefault = true;
            }

            boolean drawConditionalIndicator = sequenceFlow.getConditionExpression() != null && sequenceFlow.getConditionExpression().trim().length() > 0 && !(flowNode instanceof Gateway);
            String sourceRef = sequenceFlow.getSourceRef();
            String targetRef = sequenceFlow.getTargetRef();
            FlowElement sourceElement = bpmnModel.getFlowElement(sourceRef);
            FlowElement targetElement = bpmnModel.getFlowElement(targetRef);
            List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
            if (graphicInfoList != null && graphicInfoList.size() > 0) {
                graphicInfoList = connectionPerfectionizer(processDiagramCanvas, bpmnModel, sourceElement, targetElement, graphicInfoList);
                int[] xPoints = new int[graphicInfoList.size()];
                int[] yPoints = new int[graphicInfoList.size()];

                GraphicInfo lineCenter;
                for(int i = 1; i < graphicInfoList.size(); ++i) {
                    lineCenter = (GraphicInfo)graphicInfoList.get(i);
                    GraphicInfo previousGraphicInfo = (GraphicInfo)graphicInfoList.get(i - 1);
                    if (i == 1) {
                        xPoints[0] = (int)previousGraphicInfo.getX();
                        yPoints[0] = (int)previousGraphicInfo.getY();
                    }

                    xPoints[i] = (int)lineCenter.getX();
                    yPoints[i] = (int)lineCenter.getY();
                }

                processDiagramCanvas.drawSequenceflow(xPoints, yPoints, drawConditionalIndicator, isDefault, highLighted, scaleFactor);
                GraphicInfo labelGraphicInfo = bpmnModel.getLabelGraphicInfo(sequenceFlow.getId());
                if (labelGraphicInfo != null) {
                    processDiagramCanvas.drawLabel(sequenceFlow.getName(), labelGraphicInfo, false);
                } else if (drawSequenceFlowNameWithNoLabelDI) {
                    lineCenter = getLineCenter(graphicInfoList);
                    processDiagramCanvas.drawLabel(sequenceFlow.getName(), lineCenter, false);
                }
            }
        }

        if (flowNode instanceof FlowElementsContainer) {
            var26 = ((FlowElementsContainer)flowNode).getFlowElements().iterator();

            while(var26.hasNext()) {
                FlowElement nestedFlowElement = (FlowElement)var26.next();
                if (nestedFlowElement instanceof FlowNode && !this.isPartOfCollapsedSubProcess(nestedFlowElement, bpmnModel)) {
                    this.drawActivity(processDiagramCanvas, bpmnModel, (FlowNode)nestedFlowElement, highLightedActivities, highLightedFlows, scaleFactor, drawSequenceFlowNameWithNoLabelDI);
                }
            }
        }

    }

    protected static List<GraphicInfo> connectionPerfectionizer(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, BaseElement sourceElement, BaseElement targetElement, List<GraphicInfo> graphicInfoList) {
        GraphicInfo sourceGraphicInfo = bpmnModel.getGraphicInfo(sourceElement.getId());
        GraphicInfo targetGraphicInfo = bpmnModel.getGraphicInfo(targetElement.getId());
        MyDefaultProcessDiagramCanvas.SHAPE_TYPE sourceShapeType = getShapeType(sourceElement);
        MyDefaultProcessDiagramCanvas.SHAPE_TYPE targetShapeType = getShapeType(targetElement);
        return processDiagramCanvas.connectionPerfectionizer(sourceShapeType, targetShapeType, sourceGraphicInfo, targetGraphicInfo, graphicInfoList);
    }

    protected static MyDefaultProcessDiagramCanvas.SHAPE_TYPE getShapeType(BaseElement baseElement) {
        if (!(baseElement instanceof Task) && !(baseElement instanceof Activity) && !(baseElement instanceof TextAnnotation)) {
            if (baseElement instanceof Gateway) {
                return MyDefaultProcessDiagramCanvas.SHAPE_TYPE.Rhombus;
            } else {
                return baseElement instanceof Event ? MyDefaultProcessDiagramCanvas.SHAPE_TYPE.Ellipse : null;
            }
        } else {
            return MyDefaultProcessDiagramCanvas.SHAPE_TYPE.Rectangle;
        }
    }

    protected static GraphicInfo getLineCenter(List<GraphicInfo> graphicInfoList) {
        GraphicInfo gi = new GraphicInfo();
        int[] xPoints = new int[graphicInfoList.size()];
        int[] yPoints = new int[graphicInfoList.size()];
        double length = 0.0;
        double[] lengths = new double[graphicInfoList.size()];
        lengths[0] = 0.0;

        int p1;
        GraphicInfo graphicInfo1;
        for(p1 = 1; p1 < graphicInfoList.size(); ++p1) {
            GraphicInfo graphicInfo = (GraphicInfo)graphicInfoList.get(p1);
            graphicInfo1 = (GraphicInfo)graphicInfoList.get(p1 - 1);
            if (p1 == 1) {
                xPoints[0] = (int)graphicInfo1.getX();
                yPoints[0] = (int)graphicInfo1.getY();
            }

            xPoints[p1] = (int)graphicInfo.getX();
            yPoints[p1] = (int)graphicInfo.getY();
            length += Math.sqrt(Math.pow((double)((int)graphicInfo.getX() - (int)graphicInfo1.getX()), 2.0) + Math.pow((double)((int)graphicInfo.getY() - (int)graphicInfo1.getY()), 2.0));
            lengths[p1] = length;
        }

        double m = length / 2.0;
        p1 = 0;
        int p2 = 1;

        for(int i = 1; i < lengths.length; ++i) {
            double len = lengths[i];
            p1 = i - 1;
            p2 = i;
            if (len > m) {
                break;
            }
        }

        graphicInfo1 = (GraphicInfo)graphicInfoList.get(p1);
        GraphicInfo graphicInfo2 = (GraphicInfo)graphicInfoList.get(p2);
        double AB = (double)((int)graphicInfo2.getX() - (int)graphicInfo1.getX());
        double OA = (double)((int)graphicInfo2.getY() - (int)graphicInfo1.getY());
        double OB = lengths[p2] - lengths[p1];
        double ob = m - lengths[p1];
        double ab = AB * ob / OB;
        double oa = OA * ob / OB;
        double mx = graphicInfo1.getX() + ab;
        double my = graphicInfo1.getY() + oa;
        gi.setX(mx);
        gi.setY(my);
        return gi;
    }

    protected void drawArtifact(MyDefaultProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, Artifact artifact) {
        MyDefaultProcessDiagramGenerator.ArtifactDrawInstruction drawInstruction = (MyDefaultProcessDiagramGenerator.ArtifactDrawInstruction)this.artifactDrawInstructions.get(artifact.getClass());
        if (drawInstruction != null) {
            drawInstruction.draw(processDiagramCanvas, bpmnModel, artifact);
        }

    }

    private static void drawHighLight(MyDefaultProcessDiagramCanvas processDiagramCanvas, GraphicInfo graphicInfo) {
        processDiagramCanvas.drawHighLight((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight());
    }

    protected static MyDefaultProcessDiagramCanvas initProcessDiagramCanvas(BpmnModel bpmnModel, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
        double minX = Double.MAX_VALUE;
        double maxX = 0.0;
        double minY = Double.MAX_VALUE;
        double maxY = 0.0;

        GraphicInfo graphicInfo;
        for(Iterator var14 = bpmnModel.getPools().iterator(); var14.hasNext(); maxY = graphicInfo.getY() + graphicInfo.getHeight()) {
            Pool pool = (Pool)var14.next();
            graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
            minX = graphicInfo.getX();
            maxX = graphicInfo.getX() + graphicInfo.getWidth();
            minY = graphicInfo.getY();
        }

        List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);
        Iterator var24 = flowNodes.iterator();

        label155:
        while(var24.hasNext()) {
            FlowNode flowNode = (FlowNode)var24.next();
            GraphicInfo flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
            if (flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth() > maxX) {
                maxX = flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth();
            }

            if (flowNodeGraphicInfo.getX() < minX) {
                minX = flowNodeGraphicInfo.getX();
            }

            if (flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight() > maxY) {
                maxY = flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight();
            }

            if (flowNodeGraphicInfo.getY() < minY) {
                minY = flowNodeGraphicInfo.getY();
            }

            Iterator var18 = flowNode.getOutgoingFlows().iterator();

            while(true) {
                List graphicInfoList;
                do {
                    if (!var18.hasNext()) {
                        continue label155;
                    }

                    SequenceFlow sequenceFlow = (SequenceFlow)var18.next();
                    graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
                } while(graphicInfoList == null);

                Iterator var21 = graphicInfoList.iterator();

                while(var21.hasNext()) {
                    GraphicInfo graphicInfo1 = (GraphicInfo)var21.next();
                    if (graphicInfo1.getX() > maxX) {
                        maxX = graphicInfo1.getX();
                    }

                    if (graphicInfo1.getX() < minX) {
                        minX = graphicInfo1.getX();
                    }

                    if (graphicInfo1.getY() > maxY) {
                        maxY = graphicInfo1.getY();
                    }

                    if (graphicInfo1.getY() < minY) {
                        minY = graphicInfo1.getY();
                    }
                }
            }
        }

        List<Artifact> artifacts = gatherAllArtifacts(bpmnModel);
        Iterator var27 = artifacts.iterator();

        GraphicInfo graphicInfo2;
        while(var27.hasNext()) {
            Artifact artifact = (Artifact)var27.next();
            GraphicInfo artifactGraphicInfo = bpmnModel.getGraphicInfo(artifact.getId());
            if (artifactGraphicInfo != null) {
                if (artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth() > maxX) {
                    maxX = artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth();
                }

                if (artifactGraphicInfo.getX() < minX) {
                    minX = artifactGraphicInfo.getX();
                }

                if (artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight() > maxY) {
                    maxY = artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight();
                }

                if (artifactGraphicInfo.getY() < minY) {
                    minY = artifactGraphicInfo.getY();
                }
            }

            List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(artifact.getId());
            if (graphicInfoList != null) {
                Iterator var35 = graphicInfoList.iterator();

                while(var35.hasNext()) {
                    graphicInfo2 = (GraphicInfo)var35.next();
                    if (graphicInfo2.getX() > maxX) {
                        maxX = graphicInfo2.getX();
                    }

                    if (graphicInfo2.getX() < minX) {
                        minX = graphicInfo2.getX();
                    }

                    if (graphicInfo2.getY() > maxY) {
                        maxY = graphicInfo2.getY();
                    }

                    if (graphicInfo2.getY() < minY) {
                        minY = graphicInfo2.getY();
                    }
                }
            }
        }

        int nrOfLanes = 0;
        Iterator var30 = bpmnModel.getProcesses().iterator();

        while(var30.hasNext()) {
            org.flowable.bpmn.model.Process process = (org.flowable.bpmn.model.Process)var30.next();
            Iterator var34 = process.getLanes().iterator();

            while(var34.hasNext()) {
                Lane l = (Lane)var34.next();
                ++nrOfLanes;
                graphicInfo = bpmnModel.getGraphicInfo(l.getId());
                if (graphicInfo.getX() + graphicInfo.getWidth() > maxX) {
                    maxX = graphicInfo.getX() + graphicInfo.getWidth();
                }

                if (graphicInfo.getX() < minX) {
                    minX = graphicInfo.getX();
                }

                if (graphicInfo.getY() + graphicInfo.getHeight() > maxY) {
                    maxY = graphicInfo.getY() + graphicInfo.getHeight();
                }

                if (graphicInfo.getY() < minY) {
                    minY = graphicInfo.getY();
                }
            }
        }

        if (flowNodes.isEmpty() && bpmnModel.getPools().isEmpty() && nrOfLanes == 0) {
            minX = 0.0;
            minY = 0.0;
        }

        return new MyDefaultProcessDiagramCanvas((int)maxX + 10, (int)maxY + 10, (int)minX, (int)minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
    }

    protected static List<Artifact> gatherAllArtifacts(BpmnModel bpmnModel) {
        List<Artifact> artifacts = new ArrayList();
        Iterator var2 = bpmnModel.getProcesses().iterator();

        while(var2.hasNext()) {
            org.flowable.bpmn.model.Process process = (org.flowable.bpmn.model.Process)var2.next();
            artifacts.addAll(process.getArtifacts());
        }

        return artifacts;
    }

    protected static List<FlowNode> gatherAllFlowNodes(BpmnModel bpmnModel) {
        List<FlowNode> flowNodes = new ArrayList();
        Iterator var2 = bpmnModel.getProcesses().iterator();

        while(var2.hasNext()) {
            org.flowable.bpmn.model.Process process = (Process)var2.next();
            flowNodes.addAll(gatherAllFlowNodes((FlowElementsContainer)process));
        }

        return flowNodes;
    }

    protected static List<FlowNode> gatherAllFlowNodes(FlowElementsContainer flowElementsContainer) {
        List<FlowNode> flowNodes = new ArrayList();
        Iterator var2 = flowElementsContainer.getFlowElements().iterator();

        while(var2.hasNext()) {
            FlowElement flowElement = (FlowElement)var2.next();
            if (flowElement instanceof FlowNode) {
                flowNodes.add((FlowNode)flowElement);
            }

            if (flowElement instanceof FlowElementsContainer) {
                flowNodes.addAll(gatherAllFlowNodes((FlowElementsContainer)flowElement));
            }
        }

        return flowNodes;
    }

    protected boolean isPartOfCollapsedSubProcess(FlowElement flowElement, BpmnModel model) {
        SubProcess subProcess = flowElement.getSubProcess();
        if (subProcess != null) {
            GraphicInfo graphicInfo = model.getGraphicInfo(subProcess.getId());
            return graphicInfo != null && graphicInfo.getExpanded() != null && !graphicInfo.getExpanded() ? true : this.isPartOfCollapsedSubProcess(subProcess, model);
        } else {
            return false;
        }
    }

    public Map<Class<? extends BaseElement>, MyDefaultProcessDiagramGenerator.ActivityDrawInstruction> getActivityDrawInstructions() {
        return this.activityDrawInstructions;
    }

    public void setActivityDrawInstructions(Map<Class<? extends BaseElement>, MyDefaultProcessDiagramGenerator.ActivityDrawInstruction> activityDrawInstructions) {
        this.activityDrawInstructions = activityDrawInstructions;
    }

    public Map<Class<? extends BaseElement>, MyDefaultProcessDiagramGenerator.ArtifactDrawInstruction> getArtifactDrawInstructions() {
        return this.artifactDrawInstructions;
    }

    public void setArtifactDrawInstructions(Map<Class<? extends BaseElement>, MyDefaultProcessDiagramGenerator.ArtifactDrawInstruction> artifactDrawInstructions) {
        this.artifactDrawInstructions = artifactDrawInstructions;
    }

    protected interface ArtifactDrawInstruction {
        void draw(MyDefaultProcessDiagramCanvas var1, BpmnModel var2, Artifact var3);
    }

    protected interface ActivityDrawInstruction {
        void draw(MyDefaultProcessDiagramCanvas var1, BpmnModel var2, FlowNode var3);
    }
}
```
