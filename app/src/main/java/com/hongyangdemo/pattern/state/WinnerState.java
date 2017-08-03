package com.hongyangdemo.pattern.state;

import android.util.Log;

import com.hongyangdemo.pattern.StatePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe: 中奖的状态不允许用户有任何操作
 */

public class WinnerState implements State {
    private NewVendingMachine machine;
    public WinnerState(NewVendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        throw new IllegalStateException("非法状态！");
    }

    @Override
    public void backMoney() {
        throw new IllegalStateException("非法状态！");
    }

    @Override
    public void turnCrank() {
        throw new IllegalStateException("非法状态！");
    }

    @Override
    public void dispense() {
        Log.d(StatePattern.TAG, "你中奖了，恭喜你，商品翻倍");
        machine.setState(machine.getSoldState());
    }
}
