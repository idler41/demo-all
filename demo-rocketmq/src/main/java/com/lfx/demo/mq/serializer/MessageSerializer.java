package com.lfx.demo.mq.serializer;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-09-08 11:03:31
 */
@FunctionalInterface
public interface MessageSerializer {
    byte[] serialize(Object t);
}
