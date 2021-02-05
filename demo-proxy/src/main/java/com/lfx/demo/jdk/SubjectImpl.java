package com.lfx.demo.jdk;

/**
 * @author linfuxin
 * @date 2021-01-20 10:32:16
 */
public class SubjectImpl implements Subject{
    @Override
    public Object sayHello() {
        System.out.println("hello world");
        return "impl";
    }
}
