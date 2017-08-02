package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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


}
