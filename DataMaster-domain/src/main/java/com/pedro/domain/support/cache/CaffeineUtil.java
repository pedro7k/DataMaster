package com.pedro.domain.support.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Component;
import sun.security.rsa.RSAUtil;

import java.util.concurrent.TimeUnit;

@Component
public class CaffeineUtil {

    /**
     * 获得LoadingCache
     *
     * @param cacheLoader 参数为Object（实际为KeyType），返回值为valueType的缓存加载方法
     * @return
     */
    public static <T,E> LoadingCache<T, E> buildLoadingCache(CacheLoader<T, E> cacheLoader) {
        LoadingCache<T, E> cache = Caffeine
                .newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1024)
                .build(cacheLoader);
        return cache;
    }

    /**
     * 获得普通Cache
     */
    public static <T,E> Cache<T, E> buildCache() {
        Cache<T, E> cache = Caffeine
                .newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(1024)
                .build();
        return cache;
    }
}
