package com.hongyangdemo.pattern;

import com.hongyangdemo.pattern.singleton.Singleton1;
import com.hongyangdemo.pattern.singleton.Singleton2;
import com.hongyangdemo.pattern.singleton.Singleton3;
import com.hongyangdemo.pattern.singleton.Singleton4;
import com.hongyangdemo.pattern.singleton.Singleton5;
import com.hongyangdemo.pattern.singleton.Singleton6;
import com.hongyangdemo.pattern.singleton.Singleton7;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe: 设计模式 单例模式
 */

public class SingletonPattern {
    public static final String TAG = "SingletonPattern";

    public void testSingletonPattern(){
        //1.懒汉式 线程不安全
        Singleton1.getInstance();
        //2.懒汉式 线程安全
        Singleton2.getInstance();
        //3.饿汉式
        Singleton3.getInstance();
        //4.饿汉式 变种
        Singleton4.getInstance();
        //5.静态内部类
        Singleton5.getInstance();
        //6.枚举
        Singleton6 singleton6 = Singleton6.INSTANCE;
        //7.双重校验锁
        Singleton7.getInstance();
    }

}
