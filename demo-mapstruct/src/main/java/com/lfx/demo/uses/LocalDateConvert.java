package com.lfx.demo.uses;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author <a href="mailto:idler41@163.con">linfuxin</a> created on 2022-11-24 08:46:41
 */
@UtilityClass
public class LocalDateConvert {
    /**
     * LocalDateTime to Long
     *
     * @param time LocalDateTime
     * @return Long
     */
    public static Long timeToLong(LocalDateTime time) {
        return time != null ? time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null;
    }

    /**
     * Long to LocalDateTime
     *
     * @param value Long
     * @return LocalDateTime
     */
    public static LocalDateTime longToTime(Long value) {
        return value != null ? LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneId.systemDefault()) : null;
    }
}
