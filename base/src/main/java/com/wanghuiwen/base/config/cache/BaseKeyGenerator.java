package com.wanghuiwen.base.config.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class BaseKeyGenerator implements KeyGenerator {
    private final static int NO_PARAM_KEY = 0;
    private String keyPrefix = "base";// key前缀，用于区分不同项目的缓存，建议每个项目单独设置



    @Override
    public Object generate(Object target, Method method, Object... params) {

        StringBuilder strBuilder = new StringBuilder(30);
        strBuilder.append(keyPrefix);

        return strBuilder.toString();
    }
}
