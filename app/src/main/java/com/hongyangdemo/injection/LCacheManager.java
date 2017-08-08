package com.hongyangdemo.injection;

/**
 * author： xiongdejin
 * date: 2017/8/8
 * describe:缓存处理管理
 */

public class LCacheManager {
    public static final String TAG = "LCacheManager";
    private LCacheComponent cacheComponent;
    private static class SingletonHolder{
        private static LCacheManager instance = new LCacheManager();
    }

    private LCacheManager(){
        cacheComponent = DaggerLCacheComponent.builder().lCacheModule(new LCacheModule()).build();
        cacheComponent.inject(this);
    }

    public static LCacheManager getInstance(){
        return SingletonHolder.instance;
    }

    public  void saveCache(final String key , final String value) {
        cacheComponent.lExecutor().runTask(new Runnable() {
            @Override
            public void run() {
                cacheComponent.lCache().saveCache(key,value);
            }
        });
    }

    public  void readCache(final String key){
        cacheComponent.lExecutor().runTask(new Runnable() {
            @Override
            public void run() {
                cacheComponent.lCache().readCache(key);
            }
        });
    }
}
