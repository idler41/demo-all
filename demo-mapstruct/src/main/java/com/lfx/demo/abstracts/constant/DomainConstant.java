package com.lfx.demo.abstracts.constant;

import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-06-01 21:35:59
 */
public class DomainConstant {
    public static final String CONVERT_ID = "id";

    public static final String CONVERT_CREATE_BY = "createBy";

    public static final String CONVERT_CREATE_TIME = "createTime";

    public static final String CONVERT_UPDATE_BY = "updateBy";

    public static final String CONVERT_UPDATE_TIME = "updateTime";

    public static final String CONVERT_TENANT_ID = "tenantId";

    public static final ZoneId SYSTEM_ZONE_ID = ZoneOffset.systemDefault();
}
