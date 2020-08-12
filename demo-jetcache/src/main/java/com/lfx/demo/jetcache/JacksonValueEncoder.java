package com.lfx.demo.jetcache;

import com.alicp.jetcache.support.AbstractValueEncoder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-29 16:43:08
 */
public class JacksonValueEncoder extends AbstractValueEncoder {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    static {
        INSTANCE.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public JacksonValueEncoder() {
        super(false);
    }

    @Override
    public byte[] apply(Object o) {
        try {
            return INSTANCE.writeValueAsBytes(o);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }
}
