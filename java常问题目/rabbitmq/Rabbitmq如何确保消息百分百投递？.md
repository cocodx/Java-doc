1、每次处理完都手动签收消息。
2、可以将推送的消息存入redis缓存，消费者处理完从redis删除。开一个定时器去监听超时的消息，做重发或者其他处理。
3、开启持久化