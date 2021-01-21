# 简介

## jdk动态代理为什么不能代理class

jdk生成的代理类都会继承Proxy,所以只能代理接口。

```java
package com.sun.proxy;

import com.lfx.demo.jdk.Subject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $Proxy0 extends Proxy implements Subject {

}
```