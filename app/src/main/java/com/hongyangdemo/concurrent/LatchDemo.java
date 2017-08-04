package com.hongyangdemo.concurrent;

import android.util.Log;

import com.hongyangdemo.concurrent.latch.LatchTest1;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class LatchDemo {
    public static final String TAG = "LatchDemo";

    /**
     * 不并发去饭店
     */
    public void ordinaryToRes(){
        Log.i(TAG,"-----不并发去饭店 START-------");
        LatchTest1 test1 = new LatchTest1();
        test1.fatherToRes();
        test1.motherToRes();
        test1.meToRes();
        test1.togetherToEat();
        Log.i(TAG,"-----不并发去饭店 END-------");
    }

    /**
     * 开启多线程
     */
    public void threadToRes(){
        Log.i(TAG,"-----并发去饭店-------");
        LatchTest1 test1 = new LatchTest1();
        test1.testThread();
    }

    /**
     * 多线程利用Volatile
     */
    public void threadToResByVolatile(){
        LatchTest1 test1 = new LatchTest1();
        test1.testThreadByVolatile();
    }

    /**
     * 多线程利用Latch
     */
    public void threadToResByLatch(){
        LatchTest1 test1 = new LatchTest1();
        test1.testThreadByLatch();
    }




}
