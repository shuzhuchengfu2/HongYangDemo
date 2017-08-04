package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.concurrent.FutureTaskDemo;
import com.hongyangdemo.concurrent.LatchDemo;
import com.hongyangdemo.concurrent.SemaphoreDemo;

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

    /**
     * 测试互斥
     * @param view
     */
    public void testSemaphore(View view){
        SemaphoreDemo semaphore = new SemaphoreDemo();
        semaphore.testSemaphore();
    }

    /**
     * 测试Future
     * @param view
     */
    public void testFuture(View view){
        FutureTaskDemo demo = new FutureTaskDemo();
        demo.testFuture();
    }

}
