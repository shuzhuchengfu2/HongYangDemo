package com.hongyangdemo.pattern.observer;

import android.util.Log;

import com.hongyangdemo.pattern.ObserverPattern;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:
 */

public class Observer1 implements Observer {
    private Subject subject;

    public Observer1(Subject subject){
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        Log.d(ObserverPattern.TAG,"observer1 得到 3D 号码  -->" + msg + ", 我要记下来。");
    }
}
