package com.hongyangdemo.concurrent;

import com.hongyangdemo.concurrent.cyclicbarrier.CyclicBarrierTest;
import com.hongyangdemo.concurrent.cyclicbarrier.CyclicBarrierTest2;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:打造一个安全的门禁系统/让一组线程同时阻塞在一个位置
 */

public class CyclicBarrierDemo {
    public static final String TAG = "CyclicBarrierDemo";
    public void testCyclicBarrier(){
        CyclicBarrierTest test = new CyclicBarrierTest();
        test.testCyclicBarrier();
        CyclicBarrierTest2 test2 = new CyclicBarrierTest2();
        test2.testCyclicBarrier();
    }
}
