https://blog.csdn.net/lqzkcx3/article/details/80820327

https://www.jb51.net/article/116150.htm

https://blog.csdn.net/my_momo_csdn/article/details/95244437

https://blog.csdn.net/qq_27858615/article/details/122856888

https://www.cnblogs.com/moonlightL/p/9177511.html

https://blog.csdn.net/qq1010830256/article/details/115617442


## 自定义权限插件

1. 替换sql => 替换的sql模板不符合正常的sql语句或mybatis标签
2. 自动添加参数 => 可以将参数全部转换为map，但会导致@param修饰的单个简单参数，无法传值

综合考虑还是参数继承待父类，父类带上权限标识符，spring aop 注入值 更合适，参考ruoyi