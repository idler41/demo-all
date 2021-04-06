## 业务场景

1. 数据库读写分离
2. 需要访问不同的数据库

## 解决方案

一般方案是采用多套数据源，执行时动态切换。



### AbstractRoutingDataSource

springboot提供AbstractRoutingDataSource类实现动态切换。

- 集成dataSource接口，并持有所有datasource的map
- 自定义注解，aop拦截读取注解获取key放入threadlocal
- 自定义类继承AbstractRoutingDataSource，determineCurrentLookupKey()通过threadlocal获取key



优点：不用一个datasource绑定一个sessionFactory

缺点：扩容不灵活，代码有侵入性



### 数据库代理

RDS数据库代理是位于数据库服务端和应用服务端之间的网络代理服务，用于代理应用服务端访问数据库时的所有请求，具有高可用、高性能、可运维、简单易用等特点，同时提供自动读写分离、事务拆分、连接池等高级功能。



# 参考链接



https://www.cnblogs.com/masonlee/p/12207853.html