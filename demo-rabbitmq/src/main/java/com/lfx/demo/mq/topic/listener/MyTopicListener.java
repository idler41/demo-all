package com.lfx.demo.mq.topic.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-03-06 16:40:23
 */
@Component
public class MyTopicListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("test1-q"),
            exchange = @Exchange(value = "amq.topic", type = ExchangeTypes.TOPIC),
            key = "weight.get"))
    @RabbitHandler
    public void provinceNews(String msg) {
        System.out.println("接收消息:" + msg);
    }
}
