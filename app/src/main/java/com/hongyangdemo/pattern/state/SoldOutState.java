package com.hongyangdemo.pattern.state;

import android.util.Log;

import com.hongyangdemo.pattern.StatePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe: 售罄状态
 */

public class SoldOutState implements State {
    private NewVendingMachine machine;
    public SoldOutState(NewVendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        Log.d(StatePattern.TAG,"投币失败，商品已售罄");
    }

    @Override
    public void backMoney() {
        Log.d(StatePattern.TAG,"您未投币，想退钱么？...");
    }

    @Override
    public void turnCrank() {
        Log.d(StatePattern.TAG,"商品售罄，转动手柄也木有用");
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态！");
    }
}
