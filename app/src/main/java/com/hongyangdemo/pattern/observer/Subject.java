package com.hongyangdemo.pattern.observer;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 主题接口，所有的主题必须实现此接口
 */

public interface Subject {
    /**
     * 注册一个观察着
     *
     * @param observer
     */
    public void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     *
     * @param observer
     */
    public void removeObserver(Observer observer);

    /**
     * 通知所有的观察着
     */
    public void notifyObservers();
}
