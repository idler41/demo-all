# 一致性hash算法

一致性哈希算法在1997年由麻省理工学院提出，是一种特殊的哈希算法，在移除或者添加一个服务器时，能够尽可能小地改变已存在的服务请求与处理请求服务器之间的映射关系。一致性哈希解决了简单哈希算法在分布式哈希表( Distributed Hash Table，DHT) 中存在的动态伸缩等问题。

期望特性：映射均匀，并尽力把槽位变化时的映射变化降到最小（避免全局重新映射）。

## 环割法

### 原理

1. 初始化的时候生成分片数量 X × 环割数量 N 的固定方式编号的字符串(SHARD-1-NODE-1)，计算所有 X×N 个字符串的所有 hash 值。
2. 将所有计算出来的 hash 值放到一个排序的 Map 中，并将其中的所有元素进行排序。
3. 输入字符串的时候计算输入字符串的 hash 值，查看 hash 值介于哪两个元素之间，取小于 hash 值的那个元素对应的分片为数据的分片。

### 实现

treeMap + murmur3_32



## Maglev

### 原理

建立一个槽位的查找表(lookup table)， 对输入 k 做哈希再取余，即可映射到表中一个槽位

### 特性

查找表越大，Maglev哈希对槽位增删的容忍能力更强，映射干扰也越小

### 场景


## jumpstringhash


jump consistent hash 特性：节点可以扩容，但是不会移除节点

## 参考链接

[https://writings.sh/post/consistent-hashing-algorithms-part-4-maglev-consistent-hash](https://writings.sh/post/consistent-hashing-algorithms-part-4-maglev-consistent-hash "https://writings.sh/post/consistent-hashing-algorithms-part-4-maglev-consistent-hash")


[https://segmentfault.com/a/1190000021234695](https://segmentfault.com/a/1190000021234695 "https://segmentfault.com/a/1190000021234695")