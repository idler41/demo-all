package com.lfx.demo.rocketmq.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:39:03
 */
@Data
public class UserMessage implements Serializable {

    private Integer id;
}
