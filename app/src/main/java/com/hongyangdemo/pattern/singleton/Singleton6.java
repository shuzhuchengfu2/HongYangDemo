package com.hongyangdemo.pattern.singleton;

import android.util.Log;

import com.hongyangdemo.pattern.SingletonPattern;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe:枚举
 */

public enum  Singleton6 {
    INSTANCE;
    Singleton6(){
        Log.d(SingletonPattern.TAG,"枚举");
    }
}
