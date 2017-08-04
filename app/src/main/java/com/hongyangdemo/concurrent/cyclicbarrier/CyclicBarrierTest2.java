package com.hongyangdemo.concurrent.cyclicbarrier;

import android.util.Log;

import com.hongyangdemo.concurrent.CyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:改造后的门禁/同时过三个人
 */

public class CyclicBarrierTest2 {
    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 12;

    final CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            Log.w(CyclicBarrierDemo.TAG, "有三个同学到齐了，放行");
        }
    });

    public void goHome() throws InterruptedException, BrokenBarrierException {
        Log.w(CyclicBarrierDemo.TAG,Thread.currentThread().getName() + "已刷卡，等待开门回家~");
        barrier.await();
    }

    public void testCyclicBarrier(){
        for(int i=0;i<STUDENT_COUNT;i++){
            new Thread("学生" + i +"  "){
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
