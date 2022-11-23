package com.lfx.demo.annonation;

import org.mapstruct.Named;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-04 09:49:48
 */
@Named("operateNameConvert")
public class OperateNameConvert {

    /**
     * 操作人
     *
     * @return 操作人
     */
    @Named("operator")
    public Integer operator() {
        return 1;
    }

    /**
     * 操作时间
     *
     * @return 操作时间
     */
    @Named("operateTime")
    public LocalDateTime operateTime() {
        return LocalDateTime.now();
    }
}
