#### This is recording some methods that you dont know

##### How to query process's variableObjects?
```java
@Autowired
private RuntimeService runtimeService;

runtimeService.createProcessInstanceQuery().processInstanceId(id).includeProcessVariables().singleResult();
```

##### flowable has supported get specific kinds of object

如果传入了一个 Long 类型的变量，可以使用 runtimeService.getVariable(processInstanceId, "variableName", Long.class) 来获取。

如果传入了一个 List<Long> 类型的变量，可以使用 runtimeService.getVariable(processInstanceId, "variableName", List.class) 来获取，然后将结果转换为 List<Long> 类型。

如果传入了一个 Set 类型的变量，可以使用 runtimeService.getVariable(processInstanceId, "variableName", Set.class) 来获取，然后将结果转换为 Set 类型。

如果传入了一个 Map 类型的变量，可以使用 runtimeService.getVariable(processInstanceId, "variableName", Map.class) 来获取，然后将结果转换为 Map 类型。
