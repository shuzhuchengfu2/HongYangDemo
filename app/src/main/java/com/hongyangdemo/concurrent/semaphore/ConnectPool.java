package com.hongyangdemo.concurrent.semaphore;

import android.util.Log;

import com.hongyangdemo.concurrent.SemaphoreDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:连接池的模拟实现
 */

public class ConnectPool {
    private final List<Conn> pool = new ArrayList<Conn>(3);
    private final Semaphore semaphore = new Semaphore(3);
    public ConnectPool(){
        pool.add(new Conn());
        pool.add(new Conn());
        pool.add(new Conn());
    }

    /**
     * 请求分配连接
     * @return
     * @throws InterruptedException
     */
    public Conn getConn() throws InterruptedException{
        semaphore.acquire();
        Conn conn = null;
        synchronized (pool){
            conn = pool.remove(0);
        }
        Log.d(SemaphoreDemo.TAG,Thread.currentThread().getName()+" get a conn " + conn);
        return conn;
    }

    /**
     * 释放连接
     * @param conn
     */
    public void release(Conn conn){
        pool.add(conn);
        Log.d(SemaphoreDemo.TAG,Thread.currentThread().getName()+" release a conn " + conn);
        semaphore.release();
    }
}
