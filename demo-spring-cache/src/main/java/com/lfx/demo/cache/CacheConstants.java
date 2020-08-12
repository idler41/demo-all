package com.lfx.demo.cache;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-17 17:24:25
 */
public class CacheConstants {

    public static final String DEFAULT_KEY_PREFIX = "demo:default:";

    public static final int DEFAULT_EXPIRE_SECONDS = 24 * 3600;

    public static final class TaskGroupCache {
        public static final String CACHE_NAME = "taskGroup";
        public static final String KEY_PREFIX = DEFAULT_KEY_PREFIX + "taskGroup:";
        public static final String KEY_GROUP_ID = "id:";
        public static final String KEY_GROUP_TASK = "response:actId:";

    }

    public static final class TaskCache {
        public static final String CACHE_NAME = "task";
        public static final String KEY_PREFIX = DEFAULT_KEY_PREFIX + "task:";
        public static final String KEY_TASK_ID = "id:";
        public static final String KEY_TASK_TYPE = "type:";
        public static final String KEY_GROUP_ID = "groupId:";
    }
}
