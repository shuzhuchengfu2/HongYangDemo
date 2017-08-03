package com.hongyangdemo.pattern;

import com.hongyangdemo.pattern.template.HRWorker;
import com.hongyangdemo.pattern.template.ITWorker;
import com.hongyangdemo.pattern.template.Worker;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:
 */

public class TemplatePattern {
    public static final String TAG = "TemplatePattern";

    public void testTemplatePattern() {
        /**
         * 定义一个算法的骨架，将一些具体的步骤在子类中实现，在不改变算法骨架的前提下，修改部分步骤
         */
        Worker it = new ITWorker("鸿洋cxy");
        it.workOneDay();
        Worker hr = new HRWorker("老张 hr");
        hr.workOneDay();
    }
}
