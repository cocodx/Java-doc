1. 接收到socket
1. 将socket交给线程池
1. 一个线程处理一个socekt连接
1. 开始从socket中获取数据
1. 解析请求行
1. 解析请求头
1. 根据请求头解析Connection对应的值是keepalive还是close
1. 
1. 将Request对象交给容器进行处理---
1. 容器最终会交给对应的servlet进行处理
1. servlet中可以获取请求的各种信息，包括获取请求体
1. servlet中也可以使用response对象向客户端返回响应
1. servlet中的代码都可以执行完成后，相当于容器中已经处理完了请求，相当于请求的核心逻辑已经执行完了
1. 处理InputBuffer中的pos和lastValid，以便能够处理下一个请求


Tomcat中有两个Reqeust对象
coyote包下得Request
connector包下得Reqeust

Tomcat内部
应用层
RequestFacde------>connector.Request------->

基础层（获取数据）
coyote.Request

RequestFacde------>connector.Request------->coyote.Request

ByteChunk 字节块