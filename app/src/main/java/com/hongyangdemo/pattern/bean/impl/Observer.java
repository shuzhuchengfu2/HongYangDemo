package com.hongyangdemo.pattern.bean.impl;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 所有的观察者需要实现此接口
 */

public interface Observer {
    public void update(String msg);
}
