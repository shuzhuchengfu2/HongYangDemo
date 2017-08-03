package com.hongyangdemo.pattern.state;

import android.util.Log;

import com.hongyangdemo.pattern.StatePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:准备出商品，不允许用户操作
 */

public class SoldState implements State {
    private NewVendingMachine machine;

    public SoldState(NewVendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        Log.d(StatePattern.TAG,"正在出货，请勿投币");
    }

    @Override
    public void backMoney() {
        Log.d(StatePattern.TAG,"正在出货，没可能退币");
    }

    @Override
    public void turnCrank() {
        Log.d(StatePattern.TAG,"正在出货，请勿重复转动手柄");
    }

    @Override
    public void dispense() {
        machine.setState(machine.getNoMoneyState());
        Log.d(StatePattern.TAG,"请投币...");
    }
}
