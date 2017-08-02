package com.hongyangdemo.pattern.factory;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:西安低质量肉
 */

public class XianBadMeat implements Meat {
    @Override
    public String meatType() {
        return "西安低质量肉";
    }
}
