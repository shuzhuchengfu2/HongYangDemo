package com.hongyangdemo.pattern.appearance;

import android.util.Log;

import com.hongyangdemo.pattern.AppearancePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:播放器
 */

public class Player {
    public void on(){
        Log.d(AppearancePattern.TAG,"打开播放器 ");
    }

    public void make3DListener(){
        Log.d(AppearancePattern.TAG,"将播放器音调设为环绕立体声 ");
    }
}
