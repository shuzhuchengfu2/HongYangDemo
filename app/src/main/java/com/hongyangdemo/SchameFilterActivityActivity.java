package com.hongyangdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

public class SchameFilterActivityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schame_filter_activity);
        //assets/demo.html
        Uri uri = getIntent().getData();
        Log.d(ARouterDemoActivity.TAG,"uri:"+uri.getPath());
        ARouter.getInstance().build(uri).navigation();
        finish();
    }
}
