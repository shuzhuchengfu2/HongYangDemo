package com.hongyangdemo.pattern.factory;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:提供肉夹馍的原料工厂
 */

public interface RouJiaMoYLFactroy {
    /**
     * 生产肉
     * @return
     */
    public Meat createMeat(String type);
}
