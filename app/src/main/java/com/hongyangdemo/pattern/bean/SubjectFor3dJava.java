package com.hongyangdemo.pattern.bean;

import java.util.Observable;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:3D彩票服务号主题 继承java的Observable
 */

public class SubjectFor3dJava extends Observable {
    private String msg;
    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
        setChanged();
        notifyObservers();
    }
}
