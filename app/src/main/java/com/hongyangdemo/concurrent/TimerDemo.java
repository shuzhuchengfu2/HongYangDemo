package com.hongyangdemo.concurrent;

import com.hongyangdemo.concurrent.timer.ScheduledThreadPoolExecutorTest;
import com.hongyangdemo.concurrent.timer.TimerTest;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class TimerDemo {
    public void testTimer(){
        //1.
        //有问题的Timer
        TimerTest timerTest = new TimerTest();
        timerTest.testMain();
        //用ScheduledThreadPoolExecutor代替
        ScheduledThreadPoolExecutorTest scheduledThreadPoolExecutorTest = new ScheduledThreadPoolExecutorTest();
        scheduledThreadPoolExecutorTest.toMain();

        //2.Timer当任务抛出异常时的缺陷,如果TimerTask抛出RuntimeException，Timer会停止所有任务的运行：
        timerTest.testSecond();
        scheduledThreadPoolExecutorTest.testSecond();

    }
}
