package com.hongyangdemo.pattern;

import android.util.Log;

import com.hongyangdemo.pattern.factory.RouJiaMo;
import com.hongyangdemo.pattern.factory.XianRouJiaMoStore;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:工厂模式
 */

public class FactoryPattern {
    public static final String TAG = "FactoryPattern";
    /**
     * 1.静态工厂模式：类+静态方法
     * 2.简单工厂模式：根据口味不同提供不同的肉夹馍
     * 3.工厂方法模式
     * 4.抽象工厂模式
     */
    public void testFactoryPattern(){
        XianRouJiaMoStore xianRouJiaMoStore = new XianRouJiaMoStore();
        RouJiaMo rouJiaMo = xianRouJiaMoStore.sellRouJiaMo("TIAN");
        Log.d(TAG,rouJiaMo.name);
    }


    /**
     * Meat -->XianBadMeat
     *      -->XianGoodMeat
     * RouJiaMoYLFactroy --> XianRouJiaMoYLFactroy -->生产Meat
     *                                                  | 提供原料
     *                      RouJiaMo -->SuanRouJiaMo -->RoujiaMoStore -->XianRouJiaMoStore
     *                               -->TianRouJiaMo
     *                               -->LaRouJiaMo
     */

}
