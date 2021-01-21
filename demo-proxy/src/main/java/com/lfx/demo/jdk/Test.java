package com.lfx.demo.jdk;


import java.lang.reflect.Proxy;

/**
 * @author linfuxin
 * @date 2021-01-20 10:33:02
 */
public class Test {
    public static void main(String[] args) {
        /**
         *  开启系统参数保存生成的代理类文件，具体值可参考
         *  @see sun.misc.ProxyGenerator#saveGeneratedFiles
         */
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject subject = new SubjectImpl();
        Subject proxy = (Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                new ProxyInvocationHandler(subject)
        );
        subject.sayHello();
        proxy.sayHello();
    }
}
