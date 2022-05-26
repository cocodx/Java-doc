#### 常见log介绍
##### 常见log介绍
redo log、undo log、binlog

|常见log|作用|||
|-|-|-|-|
|redo log|当mysql崩掉了，重启的时候，快速恢复数据，很大一部分数据都是在buffer pool中，不是直接刷回磁盘|-|-|
|undo log|数据版本链，用于回滚啊，找历史数据的。|-|-|
|binlog|用来主从同步||


