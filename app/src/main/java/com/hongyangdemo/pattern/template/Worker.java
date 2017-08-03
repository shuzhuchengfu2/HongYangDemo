package com.hongyangdemo.pattern.template;

import android.util.Log;

import com.hongyangdemo.pattern.TemplatePattern;

import java.util.Date;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:
 */

public abstract class Worker {
    protected String name;

    public Worker(String name) {
        this.name = name;
    }


    /**
     * 记录一天的工作
     */
    public final void workOneDay() {
        Log.d(TemplatePattern.TAG, "-----------------work start ---------------");
        enterCompany();
        computerOn();
        work();
        computerOff();
        exitCompany();
        Log.d(TemplatePattern.TAG, "-----------------work end ---------------");
    }

    /**
     * 工作
     */
    public abstract void work();

    /**
     * 关闭电脑
     */
    private void computerOff() {
        Log.d(TemplatePattern.TAG, name + "关闭电脑");
    }

    /**
     * 打开电脑
     */
    private void computerOn() {
        Log.d(TemplatePattern.TAG, name + "打开电脑");
    }

    /**
     * 进入公司
     */
    public void enterCompany() {
        Log.d(TemplatePattern.TAG, name + "进入公司");
    }

    /**
     * 离开公司
     */
    public void exitCompany() {
        if(isNeedPrintDate()){
            Log.i(TemplatePattern.TAG,new Date().toLocaleString()+"-->");
        }
        Log.d(TemplatePattern.TAG, name + "离开公司");
    }

    /**
     * 是否需要记录时间
     * @return
     */
    public abstract boolean isNeedPrintDate();
}
