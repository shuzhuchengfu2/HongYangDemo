package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.pattern.ObserverPattern;
import com.hongyangdemo.pattern.StrategyPattern;

public class DesignPatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_pattern);
    }

    /**
     * 测试策略模式
     * @param view
     */
    public void testStrategyPattern(View view){
        StrategyPattern pattern = new StrategyPattern();
        pattern.testRole();
    }

    /**
     * 测试观察者模式
     * @param view
     */
    public void testObserverPattern(View view){
        ObserverPattern pattern = new ObserverPattern();
        pattern.testObserverPattern();
    }
}
