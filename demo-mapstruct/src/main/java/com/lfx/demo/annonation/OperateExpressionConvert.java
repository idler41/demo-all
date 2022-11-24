package com.lfx.demo.annonation;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-04 09:49:48
 */
@UtilityClass
public class OperateExpressionConvert {

    /**
     * 操作人
     *
     * @return 操作人
     */
    public static Integer operator() {
        return 1;
    }

    /**
     * 操作时间
     *
     * @return 操作时间
     */
    public static LocalDateTime operateTime() {
        return LocalDateTime.now();
    }
}
