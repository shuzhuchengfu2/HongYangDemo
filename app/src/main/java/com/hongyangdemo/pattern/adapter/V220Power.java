package com.hongyangdemo.pattern.adapter;

import android.util.Log;

import com.hongyangdemo.pattern.AdapterPattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:家用220V交流电
 */

public class V220Power {
    /**
     * 提供220V电压
     *
     * @return
     */
    public int provideV220Power() {
        Log.d(AdapterPattern.TAG,"我提供220V交流电压。");
        return 220;
    }
}
