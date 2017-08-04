package com.hongyangdemo.concurrent.cyclicbarrier;

import android.util.Log;

import com.hongyangdemo.concurrent.CyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:安全的门禁系统
 */

public class CyclicBarrierTest {
    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 10;

    final CyclicBarrier barrier = new CyclicBarrier(STUDENT_COUNT, new Runnable() {
        @Override
        public void run() {
            Log.d(CyclicBarrierDemo.TAG, "人到齐了，开门......");
        }
    });

    public void goHome() throws InterruptedException, BrokenBarrierException {
        Log.d(CyclicBarrierDemo.TAG, Thread.currentThread().getName() + "已刷卡，等待开门回家~");
        barrier.await();
        Log.d(CyclicBarrierDemo.TAG, Thread.currentThread().getName() + "放学回家~");
    }

    public void testCyclicBarrier(){
        for(int i=0;i<STUDENT_COUNT;i++){
            new Thread("学生"+i+"    "){
                @Override
                public void run() {
                    try {
                        goHome();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
