package com.hongyangdemo.pattern.decorator;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 装备的接口
 */

public interface IEquip {
    /**
     * 计算攻击力
     *
     * @return
     */
    public int caculateAttack();

    /**
     * 装备的描述
     *
     * @return
     */
    public String description();
}
