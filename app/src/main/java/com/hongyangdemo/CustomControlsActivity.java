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
     * 自定义控件一
     * @param view
     */
    public void customControlTotal(View view){
        Intent intent = new Intent(this,CustomTitleViewActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义控件二
     * @param view
     */
    public void customControlTotal2(View view){
        Intent intent = new Intent(this,CustomImageViewActivity.class);
        startActivity(intent);
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

    /**
     * 圆环交替 等待效果
     * @param view
     */
    public void customProgressBar(View view){
        Intent intent = new Intent(this,CustomProgressBarActivity.class);
        startActivity(intent);
    }

    /**
     * 视频音量调控
     * @param view
     */
    public void customVolumControlBar(View view){
        Intent intent = new Intent(this,CustomVolumControlBarActivity.class);
        startActivity(intent);
    }

    /**
     * Android 完美实现图片圆角和圆形（对实现进行分析）
     * @param view
     */
    public void circleImageView(View view){
        Intent intent = new Intent(this,CircleImageViewActivity.class);
        startActivity(intent);
    }
}
