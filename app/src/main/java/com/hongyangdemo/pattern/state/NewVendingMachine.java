package com.hongyangdemo.pattern.state;

import android.util.Log;

import com.hongyangdemo.pattern.StatePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe: 新的自动售货机：当用户转动手柄时可能会中奖
 */

public class NewVendingMachine implements State{
    private State noMoneyState;
    private State hasMoneyState;
    private State soldState;
    private State soldOutState;
    private State winnerState;

    private int count = 0;
    private State currentState = noMoneyState;

    public NewVendingMachine(int count) {
        noMoneyState = new NoMoneyState(this);
        hasMoneyState = new HasMoneyState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        winnerState = new WinnerState(this);

        this.count = count;
        if (count > 0) {
            currentState = noMoneyState;
        }else{
            currentState = soldOutState;
        }
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getNoMoneyState() {
        return noMoneyState;
    }

    public void setNoMoneyState(State noMoneyState) {
        this.noMoneyState = noMoneyState;
    }

    public State getHasMoneyState() {
        return hasMoneyState;
    }

    public void setHasMoneyState(State hasMoneyState) {
        this.hasMoneyState = hasMoneyState;
    }

    public State getSoldState() {
        return soldState;
    }

    public void setSoldState(State soldState) {
        this.soldState = soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(State soldOutState) {
        this.soldOutState = soldOutState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public void setWinnerState(State winnerState) {
        this.winnerState = winnerState;
    }

    public int getCount() {
        return count;
    }


    @Override
    public void insertMoney() {
        currentState.insertMoney();
    }

    @Override
    public void backMoney() {
        currentState.backMoney();
    }

    @Override
    public void turnCrank() {
        currentState.turnCrank();
//        if(currentState == soldState || currentState == winnerState){
//            dispense();
//        }
    }

    public void dispense() {
        if(currentState == soldState){
            Log.d(StatePattern.TAG, "发出一件商品...");
            if(count >0){
                count --;
            }
        }else if(currentState == winnerState){
            currentState.dispense();
            Log.d(StatePattern.TAG, "发出一件商品...");
            if(count >0){
                count --;
            }
        }
        currentState.dispense();
    }
}
