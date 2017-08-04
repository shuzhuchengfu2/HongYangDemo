package com.hongyangdemo.concurrent.timer;

import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class TimerTest {
    public static final String TAG = "TimerTest";

    private long start;

    public void testMain() {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "task1 invoked ! " + (System.currentTimeMillis() - start));
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
                Log.d(TAG, "task2 invoked ! " + (System.currentTimeMillis() - start));
            }
        };
        Timer timer = new Timer();
        start = System.currentTimeMillis();
        timer.schedule(task1, 1000);
        timer.schedule(task2, 3000);
        //task1 是1s后执行 task2 是3s后执行
    }

    public void testSecond() {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException("出错了");
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG,"task2 invoked!");
            }
        };
        Timer timer = new Timer();
        timer.schedule(task1, 100);
        timer.scheduleAtFixedRate(task2, new Date(), 1000);
    }
}
