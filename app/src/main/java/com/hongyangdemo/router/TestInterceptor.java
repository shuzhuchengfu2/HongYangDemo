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
 * describe: 拦截器1
 */
@Interceptor(priority = 8,name = "测试用拦截器1")
public class TestInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.d(ARouterDemoActivity.TAG,"跳转我要进行拦截===> 1");
        //处理完成，交还控制权
        callback.onContinue(postcard);

//        callback.onInterrupt(new RuntimeException("我觉得有异常"));
    }

    @Override
    public void init(Context context) {
        Log.d(ARouterDemoActivity.TAG,"拦截器初始化，会在sdk初始化的时候调用该方法，仅会调用一次 ===> 1");
    }
}
