package com.hongyangdemo.pattern.singleton;

import android.util.Log;

import com.hongyangdemo.pattern.SingletonPattern;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe:饿汉式
 */

public class Singleton3 {
    private static Singleton3 instance = new Singleton3();
    private Singleton3(){
        Log.d(SingletonPattern.TAG,"饿汉式");
    }
    public static Singleton3 getInstance(){
        return instance;
    }

}
