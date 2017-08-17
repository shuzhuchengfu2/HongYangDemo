package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hongyangdemo.widget.SwitchButton;

import java.util.Arrays;
import java.util.List;

public class SwitchButtonActivity extends AppCompatActivity {
    private SwitchButton switchButton;
    List<String> list;
    boolean isAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_button);
        switchButton = (SwitchButton) findViewById(R.id.sb);
        list = Arrays.asList("张三","李四","王五","小刘");
        switchButton.setTexts(list);
        switchButton.setOnPositionChangeListener(new SwitchButton.OnPositionChangeListener() {
            @Override
            public void currentPosition(int position) {
                Log.d(SwitchButton.TAG,"position:"+position);
            }
        });
    }

    public void btnClick(View view){
        int position = switchButton.getCurrentPosition();
        if(position ==0){
            isAdd = true;
        }else if(position == list.size() -1){
            isAdd = false;
        }

        if(isAdd){
            position ++;
        }else{
            position --;
        }

        Log.d(SwitchButton.TAG,"position:"+position);
        switchButton.setCurrentPosition(position);
    };
}
