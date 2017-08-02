package com.hongyangdemo.pattern.singleton;

import android.util.Log;

import com.hongyangdemo.pattern.SingletonPattern;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe:懒汉式 线程不安全
 */

public class Singleton1 {
    private static Singleton1 instance;
    private Singleton1(){
        Log.d(SingletonPattern.TAG,"懒汉式 线程不安全");
    }
    public static Singleton1 getInstance(){
        if(instance == null){
            instance = new Singleton1();
        }
        return instance;
    }
}
