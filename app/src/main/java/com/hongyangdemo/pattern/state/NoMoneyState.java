package com.hongyangdemo.pattern.state;

import android.util.Log;

import com.hongyangdemo.pattern.StatePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe: 没钱的状态
 */

public class NoMoneyState implements State {
    private NewVendingMachine machine;

    public NoMoneyState(NewVendingMachine machine) {
        this.machine = machine;
    }


    @Override
    public void insertMoney() {
        if(machine.getCount()>0){
            Log.d(StatePattern.TAG, "成功投入硬币");
            machine.setState(machine.getHasMoneyState());
        }else{
            machine.setState(machine.getSoldOutState());
        }

    }

    @Override
    public void backMoney() {
        Log.d(StatePattern.TAG, "您未投币，想退钱？...");
    }

    @Override
    public void turnCrank() {
        Log.d(StatePattern.TAG, "您未投币，想拿东西么？...");

    }

    @Override
    public void dispense() {
        Log.d(StatePattern.TAG, "非法状态");
    }
}
