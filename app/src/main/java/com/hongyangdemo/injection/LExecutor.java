package com.hongyangdemo.injection;

import android.util.Log;

import javax.inject.Inject;

/**
 * author： xiongdejin
 * date: 2017/8/8
 * describe:
 */

public class LExecutor {
    //默认线程池维护线程的最少数量
    private static final int DEFAULT_CPU_CORE = Runtime.getRuntime().availableProcessors();
    //线程池维护线程的最少数量
    private int coreSize = DEFAULT_CPU_CORE;

    @Inject
    public LExecutor(int coreSize) {
        this.coreSize = coreSize;
    }

    public void runTask(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        Log.e(LCacheManager.TAG,"coreSize:  = "+coreSize);
        Log.e(LCacheManager.TAG, "runTask");
        runnable.run();
    }
}
