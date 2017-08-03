package com.hongyangdemo.pattern.adapter;

import android.util.Log;

import com.hongyangdemo.pattern.AdapterPattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:适配器，把220V电压变成5V
 */

public class V5PowerAdapter implements V5Power{
    private V220Power v220Power ;

    public V5PowerAdapter(V220Power power){
        this.v220Power = power;
    }

    @Override
    public int provideV5Power() {
        int power = v220Power.provideV220Power();
        Log.d(AdapterPattern.TAG,"适配器：我悄悄的适配了电压。");
        return 5;
    }
}
