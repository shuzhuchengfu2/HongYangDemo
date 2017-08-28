package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/test/activity")
public class ARouterDemoActivity extends AppCompatActivity {
    public static String TAG = "ARouterDemoActivity";

    @Autowired
    public String name;
    @Autowired
    public int age;
    @Autowired(name = "girl")
    public boolean boy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        setContentView(R.layout.activity_arouter_demo);
        Log.d(TAG,"name:"+name +",age:"+age +",boy:"+boy);
    }
}
