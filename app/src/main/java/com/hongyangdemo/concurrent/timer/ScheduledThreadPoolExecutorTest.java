package com.hongyangdemo.concurrent.timer;

import android.util.Log;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class ScheduledThreadPoolExecutorTest {
    private long start;
    public void toMain(){
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                Log.i(TimerTest.TAG,"task1 invoked ! " + (System.currentTimeMillis() - start));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                Log.i(TimerTest.TAG,"task2 invoked ! " + (System.currentTimeMillis() - start));
            }
        };
        start = System.currentTimeMillis();
        newScheduledThreadPool.schedule(task1,1000, TimeUnit.MILLISECONDS);
        newScheduledThreadPool.schedule(task2,3000, TimeUnit.MILLISECONDS);

    }

    public void testSecond() {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException("出错了");
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                Log.d(TimerTest.TAG,"task2 invoked!");
            }
        };
        newScheduledThreadPool.schedule(task1,100, TimeUnit.MILLISECONDS);
        newScheduledThreadPool.schedule(task2,1000, TimeUnit.MILLISECONDS);
    }
}
