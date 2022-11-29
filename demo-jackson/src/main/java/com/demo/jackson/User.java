package com.demo.jackson;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-11-29 15:53:22
 */
@Data
public class User implements Serializable {

    private Integer id;

    private LocalDateTime createTime;
}
