package com.hongyangdemo.pattern.state;

import android.util.Log;

import com.hongyangdemo.pattern.StatePattern;

import java.util.Random;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe: 已投入钱的状态
 */

public class HasMoneyState implements State {
    private NewVendingMachine machine;
    private Random random = new Random();

    public HasMoneyState(NewVendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertMoney() {
        Log.d(StatePattern.TAG, "您已经投过币了，无需再投....");
    }

    @Override
    public void backMoney() {
        Log.d(StatePattern.TAG, "退币成功");
        machine.setState(machine.getNoMoneyState());
    }

    @Override
    public void turnCrank() {
        Log.d(StatePattern.TAG, "你转动了手柄");
        int winner = random.nextInt(10);
        //是否中奖
        if(winner == 0 && machine.getCount() >1){
            machine.setState(machine.getWinnerState());
        }else{
            machine.setState(machine.getSoldState());
        }
        machine.dispense();
//        if (winner == 0 && machine.getCount() > 1) {
//            machine.setState(machine.getWinnerState());
//        }
//        else {
//            machine.setState(machine.getSoldState());
//        }
    }

    @Override
    public void dispense() {
        throw new IllegalStateException("非法状态！");
    }
}
