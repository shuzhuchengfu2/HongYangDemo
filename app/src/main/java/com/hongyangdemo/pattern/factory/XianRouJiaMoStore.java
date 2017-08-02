package com.hongyangdemo.pattern.factory;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:
 */

public class XianRouJiaMoStore extends RoujiaMoStore {
    @Override
    protected RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        if("SUAN".equals(type)){
            rouJiaMo = new SuanRouJiaMo();
        }else if("LA".equals(type)){
            rouJiaMo = new LaRouJiaMo();
        }else if("TIAN".equals(type)){
            rouJiaMo = new TianRouJiaMo();
        }
        return rouJiaMo;
    }
}
