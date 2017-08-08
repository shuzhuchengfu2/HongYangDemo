package com.hongyangdemo.injection;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * author： xiongdejin
 * date: 2017/8/8
 * describe:
 */
@Module
public class LCacheModule {
    /**
     * 提供缓存对象
     * @return 返回缓存对象
     */
    @Provides
    @Singleton
    @Named("LCache")
    String provideLCacheName() {
        return "lcj";
    }

    @Provides
    @Singleton
    LCache provideLCache(@Named("LCache")String name , @Named("LCache")int maxCacheSize){
        return new LCache(name,maxCacheSize);
    }

    @Provides
    @Singleton
    @Named("LCache")
    int provideLCacheMaxSize() {
        return 600;
    }

    @Provides
    @Singleton
    @Named("provideMaxSize")
    int provideMaxSize(){
        return 300;
    }


}
