package com.lfx.demo.jetcache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.function.Function;

/**
 * @author <a href="mailto:linfx@dydf.cn">linfuxin</a>
 * @date 2020-07-29 16:23:05
 */
public class JacksonKeyConvertor implements Function<Object, Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object apply(Object o) {
        if (o == null) {
            return null;
        } else if (o instanceof String) {
            return o;
        } else {
            try {
                return objectMapper.writeValueAsString(o);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException("Could not write JSON: " + ex.getMessage(), ex);
            }
        }
    }
}
