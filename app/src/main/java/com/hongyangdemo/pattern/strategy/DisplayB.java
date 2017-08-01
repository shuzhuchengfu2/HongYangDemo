package com.hongyangdemo.pattern.strategy;

import android.util.Log;

import com.hongyangdemo.pattern.StrategyPattern;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:
 */

public class DisplayB implements DisplayInterface {
    @Override
    public void display() {
        Log.d(StrategyPattern.TAG,"样子B！");
    }
}
