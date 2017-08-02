package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.pattern.DecoratorPattern;
import com.hongyangdemo.pattern.FactoryPattern;
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

    /**
     * 测试装饰者模式
     * @param view
     */
    public void testDecoratorPattern(View view){
        DecoratorPattern pattern = new DecoratorPattern();
        pattern.testDecoratorPattern();
    }

    /**
     * 测试工厂模式
     * @param view
     */
    public void testFactoryPattern(View view){
        FactoryPattern pattern = new FactoryPattern();
        pattern.testFactoryPattern();
    }


}