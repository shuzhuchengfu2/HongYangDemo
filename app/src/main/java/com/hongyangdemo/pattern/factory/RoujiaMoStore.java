package com.hongyangdemo.pattern.factory;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:肉夹馍店
 */

public abstract class RoujiaMoStore {
    protected abstract RouJiaMo createRouJiaMo(String type);
    /**
     * 根据传入的类型卖不同的肉夹馍
     * @param type
     * @return
     */
    public RouJiaMo sellRouJiaMo(String type){
//        RouJiaMo rouJiaMo = null;
//        if("SUAN".equals(type)){
//            rouJiaMo = new SuanRouJiaMo();
//        }else if("LA".equals(type)){
//            rouJiaMo = new LaRouJiaMo();
//        }else if("TIAN".equals(type)){
//            rouJiaMo = new TianRouJiaMo();
//        }
        RouJiaMo rouJiaMo = createRouJiaMo(type);

        if(rouJiaMo == null) return null;
        rouJiaMo.prepare(new XianRouJiaMoYLFactroy(),"GOOD");
        rouJiaMo.fire();
        rouJiaMo.pack();
        return  rouJiaMo;
    }


}
