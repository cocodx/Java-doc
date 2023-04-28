#### 1.xxl-job项目已经正常启动

确认 xxl-job 的调度中心和执行器已经启动，并且能够正常连接。

#### 2.使用http的POST请求调用添加任务接口
```java
POST http://localhost:8080/xxl-job-admin/jobinfo/add
```
其中，http://localhost:8080/xxl-job-admin 是 xxl-job 调度中心的地址。

#### 3.在请求体中传入任务的信息

```java
{
    "jobGroup": 1,
    "jobDesc": "Test Job",
    "author": "admin",
    "executorRouteStrategy": "FIRST",
    "executorHandler": "demoJobHandler",
    "executorParam": "",
    "executorBlockStrategy": "SERIAL_EXECUTION",
    "executorFailStrategy": "FAIL_ALARM",
    "glueType": "BEAN",
    "glueRemark": "",
    "scheduleType": "CRON",
    "scheduleConf": "0/5 * * * * ?",
    "misfireStrategy": "DO_NOTHING",
    "jobName": "testJob",
    "status": 0,
    "triggerStatus": 0
}
```

其中，jobGroup 是任务分组 ID，jobDesc 是任务描述，executorHandler 是任务执行器名称，scheduleConf 是任务的调度表达式等信息。

#### 4.获取响应结果

发送 HTTP POST 请求，并获取响应结果。如果添加成功，响应状态码为 200。
注意，添加任务等操作需要 xxl-job 的管理员权限，需要在请求头中传入 XXL-JOB-ACCESS-TOKEN 参数，对应的值为 xxl-job 的访问令牌。其他操作的 API 接口和添加任务类似，只需要修改相应的请求体参数即可。
