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

## mybatis没有实现类如何生成动态代理？

创建jdk动态代理既可以通过代理接口的实现类生成,也可以通过代理接口生成。mybatis因为无接口实现类,所以通过代理接口生成。

1. org.apache.ibatis.builder.xml.XMLMapperBuilder#bindMapperForNamespace() 通过接口全限定名获取class
2. 通过接口生成jdk动态代理:

```java
public class XMLMapperBuilder extends BaseBuilder {
        private void bindMapperForNamespace() {
            String namespace = builderAssistant.getCurrentNamespace();
            if (namespace != null) {
                Class<?> boundType = null;
                try {
                    boundType = Resources.classForName(namespace);
                } catch (ClassNotFoundException e) {
                    //ignore, bound type is not required
                }
                if (boundType != null) {
                    if (!configuration.hasMapper(boundType)) {
                        // Spring may not know the real resource name so we set a flag
                        // to prevent loading again this resource from the mapper interface
                        // look at MapperAnnotationBuilder#loadXmlResource
                        configuration.addLoadedResource("namespace:" + namespace);
                        configuration.addMapper(boundType);
                    }
                }
            }
        }
}

public class MapperProxyFactory<T> {
    protected T newInstance(MapperProxy<T> mapperProxy) {
        return Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, mapperProxy);
    }
}
```

