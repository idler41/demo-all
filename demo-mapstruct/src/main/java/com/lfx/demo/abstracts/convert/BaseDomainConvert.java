package com.lfx.demo.abstracts.convert;

import com.lfx.demo.abstracts.util.LocalHelper;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:43:22
 */
public interface BaseDomainConvert {

    default Integer operator() {
        return LocalHelper.getSubjectId();
    }

    default LocalDateTime operateTime() {
        return LocalHelper.getOperateTime();
    }
}
