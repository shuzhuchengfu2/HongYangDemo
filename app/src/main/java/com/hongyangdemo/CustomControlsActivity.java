package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.widget.MyImageActivity;

/**
 * 自定义控件
 */
public class CustomControlsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_controls);
    }

    /**
     * ListView 的滑动删除
     * @param view
     */
    public void listViewDelete(View view){
        Intent intent = new Intent(this,ListViewDeleteActivity.class);
        startActivity(intent);
    }

    /**
     * 仿Win8的metro的UI界面
     * @param view
     */
    public void myImageView(View view){
        Intent intent = new Intent(this,MyImageActivity.class);
        startActivity(intent);
    }
}
