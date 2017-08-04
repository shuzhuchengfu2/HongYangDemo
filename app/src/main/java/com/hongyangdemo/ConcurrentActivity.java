package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.concurrent.LatchDemo;

public class ConcurrentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrent);
    }

    /**
     * 闭锁 CountDownLatch
     * @param view
     */
    public void testLatch(View view){
        LatchDemo latchDemo = new LatchDemo();
        latchDemo.ordinaryToRes();
        latchDemo.threadToRes();
        latchDemo.threadToResByVolatile();
        latchDemo.threadToResByLatch();
    }

}
