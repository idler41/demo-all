# 

MapStruct通常使用一般是定义一个接口，具体功能由编译器与插件生成。

接口定义简单但也带来一个问题： 接口只能extends，需要多种方法组合时，不容易实现。

MapStruct也支持抽象类定义，所以可通过抽象类来进行功能的组装。

ExcelConvert(BaseDomainExcelConvert)、WebConvert (BaseDomainWebConvert)、SaasDomain

非saas平台组合：

1. ExcelConvert  (只有excel功能 && 无web页面)
2. BaseDomainExcelConvert (只有excel功能 && 无web页面 && BaseDomain子类)
3. WebConvert  (没有excel功能 && 非BaseDomain)
4. BaseDomainWebConvert (没有excel功能 && 非BaseDomain && BaseDomain子类)
5. WebDomain + ExcelConvert (excel功能 && web页面)
6. BaseDomainWebConvert + BaseDomainExcelConvert  (excel功能 && web页面  && BaseDomain子类)

 

saas平台组合：

同非saas平台组合，只不过所有类都是SaasDomain的子类


## todo

是否有严格模式，让没有正确编译的class抛异常，而不是不生成class，编译通过？

saas包下是后写的代码，idea rebuild SysRoleConvert SysUserRoleConvert两个类时，target目录没有生成class，但是也没有报错
如果不报错直接部署到生产环境会很危险