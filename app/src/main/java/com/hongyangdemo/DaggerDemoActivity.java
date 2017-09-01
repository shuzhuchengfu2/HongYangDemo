package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.injection.LCacheManager;

public class DaggerDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_demo);
    }

    /**
     * @Module 修饰的类专门用来提供依赖
     * @Provides 修饰的方法用在Module类里
     * @Inject  修饰需要依赖的地方（可以是构造方法、field或者一般的方法）
     * @Component 连接@Module和注入的桥梁
     */

    /**
     * Dagger2的基本用法
     * @param view
     */
    public void basicDagger(View view){
        //关于@Module提供多个同类型@Provides
        //LCacheModule /LExecutorModule
        LCacheManager.getInstance().saveCache("key","who is lcj ?");
        LCacheManager.getInstance().readCache("key");

    }

    /**
     *Android 进阶 教你打造 Android 中的 IOC 框架 【ViewInject】
     * @param view
     */
    public void viewInject(View view){
        Intent intent = new Intent(this,ViewInjectActivity.class);
        startActivity(intent);
    }

}
