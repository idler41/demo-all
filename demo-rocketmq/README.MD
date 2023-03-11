## windows 环境搭建

1. 下载rocketmq 最新长期支持版本4.9.4
2. 配置环境变量ROCKETMQ_HOME：D:\rocketmq-all-4.9.4-bin-release
3. 启动namesrv(默认9876端口)与broker
```shell
start D:\rocketmq-all-4.9.4-bin-release\bin\mqnamesrv.cmd
start D:\rocketmq-all-4.9.4-bin-release\bin\mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
```
4. 下载、编译、启动raocket-dashboard
```shell
java -jar -Dserver.port=9090 -Drocketmq.config.namesrvAddr=127.0.0.1:9876 D:\rocketmq-all-4.9.4-bin-release\bin\rocketmq-dashboard-1.0.1-SNAPSHOT.jar 
```

## 集成springboot

手动配置NativeMessageListener存在多个线程消费同一个message问题

2.2.2 重复消费2次
2.2.3 重复消费4次

直接使用rocket-client同样会发生重复消费

原因：@RocketMQMessageListener的topic与consumerGroup不要使用rocketmq.consumer.topic等配置。 DefaultLitePullConsumer也会使用该配置，
启动后2个consumer， 但是最终只有一个Client(DefaultMQPushConsumer)与Broker通信。造成的结果就是1个clientId与broker通信但是有2个cid, 分配queue时，
只分配一半的queue，导致部分消息永远消费不了

解决：@RocketMQMessageListener 使用其他配置

## 参考链接

https://github.com/apache/rocketmq-spring

https://github.com/apache/rocketmq-dashboard