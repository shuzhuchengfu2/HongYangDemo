package com.hongyangdemo.concurrent.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:使用FutureTask来提前加载稍后要用到的数据
 */

public class PreLoaderUseFutureTask {
    /**
     * 创建一个FutureTask用来加载资源
     */
    private final FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            Thread.sleep(3000);
            return "加载资源需要3秒";
        }
    });

    public final Thread thread = new Thread(futureTask);

    public void start(){
        thread.start();
    }

    /**
     * 获取资源
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public String getRes() throws InterruptedException, ExecutionException {
        return futureTask.get();
    }

}
