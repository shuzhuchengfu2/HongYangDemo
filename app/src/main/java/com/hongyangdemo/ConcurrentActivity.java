package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.concurrent.CompletionServiceDemo;
import com.hongyangdemo.concurrent.CyclicBarrierDemo;
import com.hongyangdemo.concurrent.FutureTaskDemo;
import com.hongyangdemo.concurrent.LatchDemo;
import com.hongyangdemo.concurrent.SemaphoreDemo;
import com.hongyangdemo.concurrent.TimerDemo;

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
//        semaphore.testSemaphore();
        semaphore.testSemaphoreDemo();
    }

    /**
     * 测试Future
     * @param view
     */
    public void testFuture(View view){
        FutureTaskDemo demo = new FutureTaskDemo();
        demo.testFuture();
    }

    /**
     * 测试CyclicBarrier
     * @param view
     */
    public void testCyclicBarrier(View view){
        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        cyclicBarrierDemo.testCyclicBarrier();
    }

    /**
     * 测试Timer
     * @param view
     */
    public void testTimer(View view){
        TimerDemo timerDemo = new TimerDemo();
        timerDemo.testTimer();
    }


    /**
     * 测试CompletionService
     * @param view
     */
    public void testCompletionService(View view){
        CompletionServiceDemo completionServiceDemo = new CompletionServiceDemo();
        completionServiceDemo.testCompletionServiceDemo();
    }

}
