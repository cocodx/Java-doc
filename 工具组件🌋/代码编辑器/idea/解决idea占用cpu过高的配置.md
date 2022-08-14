```xml
# JIT 参数

# 设置用于编译的编译器线程数
-XX:CICompilerCount=2
# 开启分层编译
-XX:TieredStopAtLevel=1
# 控制最大数量嵌套调用内联
-XX:MaxInlineLevel=3
# 即时编译的东西（没弄懂...）
-XX:Tier4MinInvocationThreshold=100000
-XX:Tier4InvocationThreshold=110000
-XX:Tier4CompileThreshold=120000
```