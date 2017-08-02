package com.hongyangdemo.pattern.singleton;

import android.util.Log;

import com.hongyangdemo.pattern.SingletonPattern;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe:饿汉式 变种
 */

public class Singleton4 {
    private static Singleton4 instance = null;
    static {
        instance = new Singleton4();
    }
    private Singleton4(){
        Log.d(SingletonPattern.TAG,"饿汉式 变种");
    }
    public static Singleton4 getInstance(){
        return instance;
    }
}
