package com.hongyangdemo.pattern.singleton;

import android.util.Log;

import com.hongyangdemo.pattern.SingletonPattern;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe:双重校验锁
 */

public class Singleton7 {
    private static volatile Singleton7 instance;
    private Singleton7(){
        Log.d(SingletonPattern.TAG,"双重校验锁");
    }
    public static Singleton7 getInstance(){
        if(instance == null){
            synchronized (Singleton7.class){
                if(instance == null){
                    instance = new Singleton7();
                }
            }
        }
        return instance;
    }
}
