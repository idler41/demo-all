package com.lfx.demo.jetcache;

import com.alicp.jetcache.support.AbstractValueDecoder;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-29 16:43:08
 */
public class JacksonValueDecoder extends AbstractValueDecoder {

    private static final ObjectMapper INSTANCE = new ObjectMapper();


    public JacksonValueDecoder() {
        super(false);
    }

    @Override
    protected Object doApply(byte[] buffer) throws Exception {
        return INSTANCE.readValue(buffer, Object.class);
    }
}
