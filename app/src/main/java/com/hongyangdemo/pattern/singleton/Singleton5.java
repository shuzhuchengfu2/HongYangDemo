package com.hongyangdemo.pattern.singleton;

import android.util.Log;

import com.hongyangdemo.pattern.SingletonPattern;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe:静态内部类
 */

public class Singleton5 {
    private Singleton5(){
        Log.d(SingletonPattern.TAG,"静态内部类");
    }
    private static class SingletonHolder{
        private static final Singleton5 INSTANCE = new Singleton5();
    }
    public static final Singleton5 getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
