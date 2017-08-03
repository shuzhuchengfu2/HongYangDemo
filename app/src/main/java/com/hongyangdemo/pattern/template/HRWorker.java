package com.hongyangdemo.pattern.template;

import android.util.Log;

import com.hongyangdemo.pattern.TemplatePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:hr
 */

public class HRWorker extends Worker {
    public HRWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.d(TemplatePattern.TAG,name + "看简历-打电话-接电话");
    }

    @Override
    public boolean isNeedPrintDate() {
        return false;
    }
}
