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
public class LExecutorModule {
    /**
     * 提供app 多任务最少维护线程个数
     * @return 返回多任务最少维护线程个数
     */
    @Provides
    @Singleton
    LExecutor provideLExecutor() {
        return new LExecutor(10);
    }

    @Named("WriteLExecutor")
    @Provides
    @Singleton
    LExecutor provideWriteLExecutor(){
        return new LExecutor(5);
    }
}
