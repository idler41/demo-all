# 分布式id生成

手动设置dataCenterId和workId适合一般的小型应用，不依赖zk或db

## 思路一

ip后三位 + 服务编号 作为 dataCenterId和workId

### 优点

- 无三方依赖，不需要独立部署，可组件化集成到服务
- 服务部署时必须关注dataCenterId和workId

### 缺点

- 服务编号相同的应用部署在同一局域网时，子网掩码必须一样
- 服务编号相同的应用最多部署255个实例

## 思路二

通过本地环境变量设置dataCenterId和workId

需要运维支持：服务弹性伸缩时，dataCenterId和workId不能同时相同 
