package com.hongyangdemo.pattern.strategy;

import android.util.Log;

import com.hongyangdemo.pattern.StrategyPattern;
import com.hongyangdemo.pattern.strategy.RunInterface;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:
 */

public class RunA implements RunInterface {

    @Override
    public void run() {
        Log.d(StrategyPattern.TAG,"金蝉脱壳！");
    }
}
