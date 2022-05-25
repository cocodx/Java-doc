TCP的三次握手和四次挥手示意图

![image](../images/Snipaste_2022-05-26_00-51-31.png)

握手：client发送一个syn，返回一个syn和ack，再发送ack。建立连接

挥手：client发送FIN ACK，返回ACK，返回FIN，再发送ACK。断开连接