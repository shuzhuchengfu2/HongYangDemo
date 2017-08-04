package com.hongyangdemo.concurrent.semaphore;

import android.util.Log;

import com.hongyangdemo.concurrent.SemaphoreDemo;

import java.util.concurrent.Semaphore;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:假设我们公司只有一台打印机，我们需要对这台打印机的打印操作进行互斥控制
 *  使用信号量机制，实现互斥访问打印机
 */

public class MutexPrint {
    /**
     * 定义初始值为1的信号量
     */
    private final Semaphore semaphore = new Semaphore(1);

    /**
     * 模拟打印
     * @param str
     * @throws InterruptedException
     */
    public void print(String str) throws InterruptedException{
        //请求被许可
        semaphore.acquire();
        Log.i(SemaphoreDemo.TAG,Thread.currentThread().getName()+" enter ...");
        Thread.sleep(1000);
        Log.d(SemaphoreDemo.TAG,Thread.currentThread().getName() + "正在打印 ..." + str);
        Log.i(SemaphoreDemo.TAG,Thread.currentThread().getName()+" out ...");
        semaphore.release();
    }

    public void testSemaphore(){
        //开启10个线程抢占打印机
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        print("hello world");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
