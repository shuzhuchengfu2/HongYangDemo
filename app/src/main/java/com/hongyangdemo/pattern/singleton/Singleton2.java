package com.hongyangdemo.pattern.singleton;

import android.util.Log;

import com.hongyangdemo.pattern.SingletonPattern;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe:懒汉式 线程安全
 */

public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2(){
        Log.d(SingletonPattern.TAG,"懒汉式 线程安全");
    }
    public static synchronized Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
