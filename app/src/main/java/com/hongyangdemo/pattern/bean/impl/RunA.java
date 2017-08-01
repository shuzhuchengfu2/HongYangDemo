package com.hongyangdemo.pattern.bean.impl;

import android.util.Log;

import com.hongyangdemo.pattern.StrategyPattern;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:
 */

public class RunA implements RunInterface{

    @Override
    public void run() {
        Log.d(StrategyPattern.TAG,"金蝉脱壳！");
    }
}
