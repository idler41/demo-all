package com.lfx.demo.config.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-28 13:45:47
 */
@ConfigurationProperties(prefix = "caching.local")
@Data
public class CacheSpecMap {

    private Map<String, String> specs;
}
