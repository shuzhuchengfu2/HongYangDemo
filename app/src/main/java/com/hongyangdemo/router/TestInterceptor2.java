package com.hongyangdemo.router;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.hongyangdemo.ARouterDemoActivity;

/**
 * author： xiongdejin
 * date: 2017/8/28
 * describe: 拦截器2
 */
@Interceptor(priority = 4,name = "测试用拦截器2")
public class TestInterceptor2 implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.d(ARouterDemoActivity.TAG,"跳转我要进行拦截 ===> 2");
        //处理完成，交还控制权
        callback.onContinue(postcard);

//        postcard.setTag(new Object());  如果设置了tag就相当于被拦截

//        callback.onInterrupt(new RuntimeException("我觉得有异常"));
    }

    @Override
    public void init(Context context) {
        Log.d(ARouterDemoActivity.TAG,"拦截器初始化，会在sdk初始化的时候调用该方法，仅会调用一次 ===> 2");
    }
}
