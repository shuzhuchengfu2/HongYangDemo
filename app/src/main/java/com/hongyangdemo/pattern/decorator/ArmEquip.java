package com.hongyangdemo.pattern.decorator;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 武器 攻击力+20
 */

public class ArmEquip implements IEquip {
    @Override
    public int caculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "屠龙刀";
    }
}
