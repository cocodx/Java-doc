#### _cat


#### PUT,POST新增数据
新版本里面，在url里边，原TYPE类型必选，改为_doc。

![image](../images/Snipaste_2022-07-15_07-00-58.png)

_seq_no 并发控制字段，每次更新就会+1，用来做乐观锁

https://www.elastic.co/guide/cn/elasticsearch/guide/current/delete-doc.html