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



## 参考链接

https://github.com/apache/rocketmq-spring

https://github.com/apache/rocketmq-dashboard