package com.hongyangdemo.pattern.adapter;

import android.util.Log;

import com.hongyangdemo.pattern.AdapterPattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:手机
 */

public class Mobile {
    /**
     * 充电
     * @param power
     */
    public void inputPower(V5Power power){
        int provideV5Power = power.provideV5Power();
        Log.d(AdapterPattern.TAG,"手机（客户端）：我需要5V电压充电，现在是-->" + provideV5Power + "V");
    }
}
