package com.hongyangdemo.pattern.factory;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 西安肉夹馍原料厂
 */

public class XianRouJiaMoYLFactroy implements RouJiaMoYLFactroy{

    @Override
    public Meat createMeat(String type) {
        if("GOOD".equals(type)){
            return new XianGoodMeat();
        }else if("BAD".equals(type)){
            return new XianBadMeat();
        }
        return null;
    }
}
