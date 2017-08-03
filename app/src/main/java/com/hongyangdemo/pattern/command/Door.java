package com.hongyangdemo.pattern.command;

import android.util.Log;

import com.hongyangdemo.pattern.CommandPattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:门
 */

public class Door {
    public void open() {
        Log.d(CommandPattern.TAG,"打开门");
    }

    public void close() {
        Log.d(CommandPattern.TAG,"关闭门");
    }
}
