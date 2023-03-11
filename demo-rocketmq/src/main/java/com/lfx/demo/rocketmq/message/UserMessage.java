package com.lfx.demo.rocketmq.message;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2023-02-14 11:39:03
 */
@Data
public class UserMessage implements Serializable {

    @NotNull
//    @Min(0)
    private Integer id;

    @Valid
    private DetailMessage detailMessage;

    @Data
    public static class DetailMessage {

        @NotBlank
        private String info;
    }
}
