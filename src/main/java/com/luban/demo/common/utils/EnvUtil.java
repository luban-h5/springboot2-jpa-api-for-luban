package com.luban.demo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取环境变量
 *
 * @author Yang Hao
 * @date 2020/1/12 18:40
 */
public class EnvUtil {

    private static final Map<String, String> cache = new HashMap(16);

    public static String getStrWithCache(Environment env, String key) {
        return getStrWithCache(env, key, null);
    }

    public static String getStrWithCache(Environment env, String key, String defaul) {
        String val = cache.get(key);
        if (StringUtils.isNotEmpty(val)) {
            return cache.get(key);
        }

        val = env.getProperty(key);
        if (StringUtils.isNotEmpty(val)) {
            cache.put(key, val);
        } else {
            val = defaul;
        }
        return val;
    }

    /**
     * 获取环境变量中int类型属性值
     *
     * @param env
     * @param key
     * @param defaul
     * @return
     */
    public static Integer getIntWithCache(Environment env, String key, Integer defaul) {
        Integer res = env.getProperty(key, Integer.class);
        return res == null ? defaul : res;
    }
}
