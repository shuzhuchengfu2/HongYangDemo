package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

public class MainActivity extends AppCompatActivity implements NavigationCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 自定义控件
     * @param view
     */
    public void customControl(View view){
        Intent intent = new Intent(this,CustomControlsActivity.class);
        startActivity(intent);
    }

    /**
     * 设计模式
     * @param view
     */
    public void designPattern(View view){
        Intent intent = new Intent(this,DesignPatternActivity.class);
        startActivity(intent);
    }

    /**
     * 网络
     * @param view
     */
    public void netWork(View view){
        Intent intent = new Intent(this,NetworkActivity.class);
        startActivity(intent);
    }

    /**
     * JAVA并发
     * @param view
     */
    public void concurrent(View view){
        Intent intent = new Intent(this,ConcurrentActivity.class);
        startActivity(intent);
    }

    /**
     * 依赖注入
     * @param view
     */
    public void injection(View view){
        Intent intent = new Intent(this,DaggerDemoActivity.class);
        startActivity(intent);
    }

    /**
     * 推荐控件
     * @param view
     */
    public void recommend(View view){
        Intent intent = new Intent(this,RecommendControlsActivity.class);
        startActivity(intent);
    }


    /**
     * 热修复
     * @param view
     */
    public void hotfix(View view){
        Intent intent = new Intent(this,HotFixActivity.class);
        startActivity(intent);
    }


    /**
     * 路由
     * @param view
     */
    public void aRouter(View view){
        //普通路由
//        ARouter.getInstance().build("/test/activity")
//                .withString("name","张三")
//                .withInt("age",100)
//                .withBoolean("girl",true)
//                .navigation();
        //高级路由：结果返回
        ARouter.getInstance().build("/test/activity")
                .withString("name","张三")
                .withInt("age",100)
                .withBoolean("girl",true)
                .navigation(this,this);

    }

    /**
     * 其他
     * @param view
     */
    public void others(View view){
        Intent intent = new Intent(this,OthersActivity.class);
        startActivity(intent);
    }

    /**
     * 源码解析
     * @param view
     */
    public void sourceCode(View view){
        Intent intent = new Intent(this,SourceCodeActivity.class);
        startActivity(intent);
    }
















    @Override
    public void onFound(Postcard postcard) {
        Log.d(ARouterDemoActivity.TAG,"找到了 ---->");

    }

    @Override
    public void onLost(Postcard postcard) {
        Log.d(ARouterDemoActivity.TAG,"没找到了 ---->");
    }

    @Override
    public void onArrival(Postcard postcard) {
        Log.d(ARouterDemoActivity.TAG,"跳转结束 ---->");
    }

    @Override
    public void onInterrupt(Postcard postcard) {
        Log.d(ARouterDemoActivity.TAG,"跳转被拦截 ---->");
    }
}
