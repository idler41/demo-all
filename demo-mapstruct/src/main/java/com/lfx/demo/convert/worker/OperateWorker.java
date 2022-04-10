package com.lfx.demo.convert.worker;

import org.mapstruct.Named;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-04-09 20:00:43
 */
@Named("OperateWorker")
public class OperateWorker {

    @Named("operator")
    public Integer operator() {
        return null;
    }

    @Named("operateTime")
    public LocalDateTime operateTime() {
        return null;
    }
}
