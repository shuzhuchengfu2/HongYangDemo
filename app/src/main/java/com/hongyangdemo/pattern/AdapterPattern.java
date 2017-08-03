package com.hongyangdemo.pattern;

import com.hongyangdemo.pattern.adapter.Mobile;
import com.hongyangdemo.pattern.adapter.V220Power;
import com.hongyangdemo.pattern.adapter.V5PowerAdapter;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe: 适配器模式
 */

public class AdapterPattern {
    public static final String TAG = "AdapterPattern";

    public void testAdapterPattern(){
        /**
         * 将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。
         */
        Mobile mobile = new Mobile();
        V5PowerAdapter adapter = new V5PowerAdapter(new V220Power());
        mobile.inputPower(adapter);
    }
}
