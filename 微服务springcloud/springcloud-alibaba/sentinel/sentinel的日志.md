springboot项目启动之后，会在userhome目录，C:\Users\amazfit\logs\csp\

metrics.log 记录每秒的统计信息
![image](../../../images/Snipaste_2022-05-27_03-05-00.png)
|参数|含义|
|-|-|
|timestamp|时间戳|
|datetime|时间|
|resource|访问的资源|
|p|通过的请求数|
|block|被阻止的请求|
|s|成功执行完成的请求数|
|e|用户自定义的异常|
|rt|平均响应时长，单位为ms|

![image](../../../images/Snipaste_2022-05-27_03-29-16.png)

![image](../../../images/Snipaste_2022-05-27_03-33-22.png)
sentinel-record.log，记录sentinel的重要的行为,写入的规则
![image](../../../images/Snipaste_2022-05-27_03-35-42.png)
具体的实时统计记录xx-metrics.log.${date}的文件中。

