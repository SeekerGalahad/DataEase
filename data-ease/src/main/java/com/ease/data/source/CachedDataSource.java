package com.ease.data.source;


import com.ease.data.cache.DataCache;

public abstract class CachedDataSource<T> {

    private DataCache<T> cache;

    public void setCache(DataCache<T> cache) {
        this.cache = cache;
    }

    protected void cacheData(T data) {
        cache.cacheData(data);
    }
}
