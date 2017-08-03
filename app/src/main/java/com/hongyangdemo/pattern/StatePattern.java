package com.hongyangdemo.pattern;

import android.util.Log;

import com.hongyangdemo.pattern.state.NewVendingMachine;
import com.hongyangdemo.pattern.state.VendingMachine;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:状态模式
 */

public class StatePattern {
    public static final String TAG = "StatePattern";

    public void testStatePattern(){
        /**
         * 当对象内部状态改变时，它的行为随着状态的改变而改变，看起来好像重新初始化了一个类
         */

        VendingMachine machine = new VendingMachine(10);
        machine.insertMoney();
        machine.backMoney();

        Log.d(TAG,"-----------");

        machine.insertMoney();
        machine.turnCrank();

        Log.d(TAG,"-----压力测试------");
        machine.insertMoney();
        machine.insertMoney();
        machine.turnCrank();
        machine.turnCrank();
        machine.backMoney();
        machine.turnCrank();

        Log.d(StatePattern.TAG, "------我要中奖-----");
        NewVendingMachine newVendingMachine = new NewVendingMachine(10);
        newVendingMachine.insertMoney();
        newVendingMachine.backMoney();
        for(int i=0;i<20;i++){
            Log.d(StatePattern.TAG, "------第"+i+"次尝试-----");
            newVendingMachine.insertMoney();
            newVendingMachine.turnCrank();
        }


    }
}
