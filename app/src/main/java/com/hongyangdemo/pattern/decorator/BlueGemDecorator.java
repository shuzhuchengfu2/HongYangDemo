package com.hongyangdemo.pattern.decorator;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 蓝宝石装饰品 每颗攻击力+5
 */

public class BlueGemDecorator implements IEquipDecorator {

    private IEquip equip;

    public BlueGemDecorator(IEquip equip){
        this.equip = equip;
    }

    @Override
    public int caculateAttack() {
        return 5 + equip.caculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + "+ 蓝宝石";
    }
}
