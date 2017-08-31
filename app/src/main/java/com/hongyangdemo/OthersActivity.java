package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OthersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
    }

    /**
     * Android Handler 异步消息处理机制的妙用 创建强大的图片加载类
     * @param view
     */
    public void imageLoad(View view){
        Intent intent = new Intent(this,HandlerImageLoaderActivity.class);
        startActivity(intent);
    }
}
