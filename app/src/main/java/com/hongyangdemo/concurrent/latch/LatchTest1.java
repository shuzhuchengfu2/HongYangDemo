package com.hongyangdemo.concurrent.latch;

import android.util.Log;

import com.hongyangdemo.concurrent.LatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class LatchTest1 {
    /**
     *  一家人要吃饭，通知到饭店集合，三个人在不同的地方上班，必须要三个人都到齐了才能开饭
     */
    /**
     * 模拟爸爸去饭店
     */
    public void fatherToRes() {
        Log.d(LatchDemo.TAG,"爸爸步行去饭店需要3小时。");
    }

    /**
     * 模拟我去饭店
     */
    public void motherToRes() {
        Log.d(LatchDemo.TAG,"妈妈挤公交去饭店需要2小时。");
    }

    /**
     * 模拟妈妈去饭店
     */
    public void meToRes() {
        Log.d(LatchDemo.TAG,"我乘地铁去饭店需要1小时。");
    }

    /**
     * 模拟一家人到齐了
     */
    public void togetherToEat() {
        Log.d(LatchDemo.TAG,"一家人到齐了，开始吃饭。");
    }

    /**
     * 多线程无锁
     */
    public void testThread(){
        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 无锁-------");
                fatherToRes();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 无锁-------");
                motherToRes();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 无锁-------");
                meToRes();
            }
        }.start();
        togetherToEat();
    }

    public volatile int i =3;

    /**
     * 利用Volatile控制线程
     */
    public void testThreadByVolatile(){
        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 Volatile-------");
                fatherToRes();
                i--;
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 Volatile-------");
                motherToRes();
                i--;
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 Volatile-------");
                meToRes();
                i--;
            }
        }.start();
        while(i !=0);
        togetherToEat();
    }

    private static CountDownLatch latch = new CountDownLatch(3);

    /**
     * 利用Latch控制线程
     */
    public void testThreadByLatch(){
        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 Latch-------");
                fatherToRes();
                latch.countDown();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 Latch-------");
                motherToRes();
                latch.countDown();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                Log.d(LatchDemo.TAG,"-----多线程去饭店 Latch-------");
                meToRes();
                latch.countDown();
            }
        }.start();
        try{
            latch.await();
            togetherToEat();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
