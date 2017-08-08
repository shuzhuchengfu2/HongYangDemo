package com.hongyangdemo.injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * author： xiongdejin
 * date: 2017/8/8
 * describe:
 */
@Component(modules = {LCacheModule.class,LExecutorModule.class})
@Singleton
public interface LCacheComponent {
    LCache lCache();   // app缓存

    LExecutor lExecutor();  // app多任务线程池

    void inject(LCacheManager lCacheManager);
}
