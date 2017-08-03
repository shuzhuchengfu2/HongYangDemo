package com.hongyangdemo.pattern.appearance;

import android.util.Log;

import com.hongyangdemo.pattern.AppearancePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:爆米花机
 */

public class PopcornPopper {
    public void on(){
        Log.d(AppearancePattern.TAG,"打开爆米花机");
    }

    public void makePopcorn(){
        Log.d(AppearancePattern.TAG,"制作爆米花");
    }
}
