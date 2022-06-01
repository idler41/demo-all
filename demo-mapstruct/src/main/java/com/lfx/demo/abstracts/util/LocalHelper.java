package com.lfx.demo.abstracts.util;

import java.time.LocalDateTime;

import static com.lfx.demo.abstracts.constant.DomainConstant.SYSTEM_ZONE_ID;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:39:03
 */
public class LocalHelper {
    private static final ThreadLocal<LocalDateTime> OPERATE_TIME_LOCAL = new ThreadLocal<>();
    public static final ThreadLocal<Integer> SUBJECT_THREAD_LOCAL = new ThreadLocal<>();

    public static void setOperateTimeLocal(LocalDateTime operateTime) {
        OPERATE_TIME_LOCAL.set(operateTime);
    }

    public static LocalDateTime getOperateTime() {
        LocalDateTime now = OPERATE_TIME_LOCAL.get();
        if (now != null) {
            return now;
        }
        now = LocalDateTime.now(SYSTEM_ZONE_ID);
        setOperateTimeLocal(now);
        return now;
    }

    public static Integer getSubjectId() {
        return SUBJECT_THREAD_LOCAL.get();
    }

    public static void setSubjectId(Integer subjectId) {
        SUBJECT_THREAD_LOCAL.set(subjectId);
    }

    public static void clear() {
        OPERATE_TIME_LOCAL.remove();
        SUBJECT_THREAD_LOCAL.remove();
    }

}
