package com.hongyangdemo.pattern;

import android.util.Log;

import com.hongyangdemo.pattern.decorator.ArmEquip;
import com.hongyangdemo.pattern.decorator.BlueGemDecorator;
import com.hongyangdemo.pattern.decorator.IEquip;
import com.hongyangdemo.pattern.decorator.RedGemDecorator;
import com.hongyangdemo.pattern.decorator.ShoeEquip;
import com.hongyangdemo.pattern.decorator.YellowGemDecorator;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:装饰者模式
 */

public class DecoratorPattern {
    public static final String TAG = "DecoratorPattern";
    public void testDecoratorPattern(){
        // 一个镶嵌2颗红宝石，1颗蓝宝石的靴子
        Log.i(TAG,"-------");
        Log.i(TAG," 一个镶嵌2颗红宝石，1颗蓝宝石的靴子");
        IEquip equip = new RedGemDecorator(new RedGemDecorator(new BlueGemDecorator(new ShoeEquip())));
        Log.d(TAG,"攻击力  : " + equip.caculateAttack());
        Log.d(TAG,"描述 :" + equip.description());
        Log.i(TAG,"-------");
        // 一个镶嵌1颗红宝石，1颗蓝宝石的武器
        Log.i(TAG," 一个镶嵌1颗红宝石，1颗蓝宝石,1颗黄宝石的武器");
        equip = new RedGemDecorator(new BlueGemDecorator(new YellowGemDecorator(new ArmEquip())));
        Log.d(TAG,"攻击力  : " + equip.caculateAttack());
        Log.d(TAG,"描述 :" + equip.description());
    }
}
