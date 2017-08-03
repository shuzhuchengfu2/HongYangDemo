package com.hongyangdemo.pattern.appearance;

import android.util.Log;

import com.hongyangdemo.pattern.AppearancePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:投影仪
 */

public class Projector {
    public void on(){
        Log.d(AppearancePattern.TAG,"打开投影仪");
    }

    public void open(){
        Log.d(AppearancePattern.TAG,"放下投影仪投影区");
    }
}
