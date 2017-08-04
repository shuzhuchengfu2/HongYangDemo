package com.hongyangdemo.concurrent;

import android.util.Log;

import com.hongyangdemo.concurrent.semaphore.Conn;
import com.hongyangdemo.concurrent.semaphore.ConnectPool;
import com.hongyangdemo.concurrent.semaphore.MutexPrint;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class SemaphoreDemo {
    public static final String TAG = "SemaphoreDemo";
    public void testSemaphore(){
        Log.i(TAG,"ConnectPool START");
        final ConnectPool connectPool = new ConnectPool();
        // 第一个线程占用1个连接3秒
        new Thread(){
            @Override
            public void run() {
                try {
                    Conn conn = connectPool.getConn();
                    Thread.sleep(3000);
                    connectPool.release(conn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //开启3个线程请求分配连接
        for(int i=0;i<3;i++){
            new Thread(){
                @Override
                public void run() {
                    try {
                        Conn conn = connectPool.getConn();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        Log.i(TAG,"ConnectPool END");
        Log.e(TAG,"============================");
        Log.i(TAG,"MutexPrint START");
        MutexPrint mutexPrint = new MutexPrint();
        mutexPrint.testSemaphore();
        Log.i(TAG,"MutexPrint END");
    }
}
