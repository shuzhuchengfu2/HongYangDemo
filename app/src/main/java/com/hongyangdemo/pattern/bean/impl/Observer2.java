package com.hongyangdemo.pattern.bean.impl;

import android.util.Log;

import com.hongyangdemo.pattern.ObserverPattern;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:
 */

public class Observer2 implements Observer {

    private Subject subject;

    public Observer2(Subject subject){
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        Log.d(ObserverPattern.TAG,"observer2 得到 3D 号码 -->" + msg + "我要告诉舍友们。");
    }
}
