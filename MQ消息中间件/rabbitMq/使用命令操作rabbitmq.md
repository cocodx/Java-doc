使用命令设置用户拥有访问权限
```java
sudo rabbitmqctl list_users
列出用户权限
Listing users ...
mq  [administrator]
guest  [administrator]


sudo rabbitmqctl  set_permissions -p / mq '.*' '.*' '.*'
```
注意：添加virtual host的时候，要在name之前加斜杠