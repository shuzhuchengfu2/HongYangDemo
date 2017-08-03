package com.hongyangdemo.pattern.command;

import android.util.Log;

import com.hongyangdemo.pattern.CommandPattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:电灯
 */

public class Light {
    public void on() {
        Log.d(CommandPattern.TAG,"打开电灯");
    }

    public void off() {
        Log.d(CommandPattern.TAG,"关闭电灯");
    }
}
