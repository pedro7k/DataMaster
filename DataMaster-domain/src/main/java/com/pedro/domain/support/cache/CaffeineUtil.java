package com.pedro.domain.support.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Component;
import sun.security.rsa.RSAUtil;

import java.util.concurrent.TimeUnit;

public class CaffeineUtil<T, E> {

    /**
     * 获得Cache
     *
     * @param cacheLoader 参数为Object（实际为KeyType），返回值为valueType的缓存加载方法
     * @return
     */
    public LoadingCache<T, E> buildCache(CacheLoader<T, E> cacheLoader) {
        LoadingCache<T, E> cache = Caffeine
                .newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1024)
                .build(cacheLoader);
        return cache;
    }
}
