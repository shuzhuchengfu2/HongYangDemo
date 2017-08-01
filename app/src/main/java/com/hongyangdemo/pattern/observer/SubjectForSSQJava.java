package com.hongyangdemo.pattern.observer;

import java.util.Observable;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 一个双色球的服务号主题
 */

public class SubjectForSSQJava extends Observable {
    private String msg;


    public String getMsg() {
        return msg;
    }


    /**
     * 主题更新消息
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        setChanged();
        notifyObservers();
    }
}
