package com.hongyangdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hongyangdemo.injection.ContentView;
import com.hongyangdemo.injection.ViewInject;
import com.hongyangdemo.injection.ViewInjectUtils;

/**
 * Android 进阶 教你打造 Android 中的 IOC 框架 【ViewInject】
 */
@ContentView(R.layout.activity_view_inject)
public class ViewInjectActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.tv_first)
    private TextView tv_first;
    @ViewInject(R.id.btn_second)
    private Button btn_second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        tv_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_first:
                tv_first.setText("first");
                break;
            case R.id.btn_second:
                btn_second.setText("second");
                break;
        }
    }
}
