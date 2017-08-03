package com.hongyangdemo.pattern.template;

import android.util.Log;

import com.hongyangdemo.pattern.TemplatePattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:程序员
 */

public class ITWorker extends Worker {
    public ITWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        Log.d(TemplatePattern.TAG,name + "写程序-测bug-fix bug");
    }

    @Override
    public boolean isNeedPrintDate() {
        return true;
    }
}
