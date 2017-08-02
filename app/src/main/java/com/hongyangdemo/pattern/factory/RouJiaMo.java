package com.hongyangdemo.pattern.factory;

import android.util.Log;

import com.hongyangdemo.pattern.FactoryPattern;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:肉夹馍抽象类
 */

public abstract class RouJiaMo {
    public String name;

    /**
     * 准备工作
     */
    public void prepare(RouJiaMoYLFactroy factroy,String type) {
        Log.d(FactoryPattern.TAG,factroy.createMeat(type).meatType());
        Log.d(FactoryPattern.TAG,"剁肉-完成准备工作");
    }

    /**
     * 使用你们的专用袋-包装
     */
    public void pack() {
        Log.d(FactoryPattern.TAG,"肉夹馍-专用袋-包装");
    }

    /**
     * 秘制设备-烘烤2分钟
     */
    public void fire() {
        Log.d(FactoryPattern.TAG,"肉夹馍-专用设备-烘烤");
    }
}
