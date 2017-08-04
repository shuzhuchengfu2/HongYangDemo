package com.hongyangdemo.concurrent;

import android.util.Log;

import com.hongyangdemo.concurrent.futuretask.BookInstance;
import com.hongyangdemo.concurrent.futuretask.PreLoaderUseFutureTask;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class FutureTaskDemo {
    public static final String TAG = "FutureTaskDemo";
    public void testFuture(){
        new Thread(){
            @Override
            public void run() {
                try {
                    PreLoaderUseFutureTask task = new PreLoaderUseFutureTask();
                    task.start();
                    Thread.sleep(2000);
                    Log.d(TAG,System.currentTimeMillis() + "：开始加载资源");
                    String res = task.getRes();
                    Log.d(TAG,"资源内容："+res);
                    Log.d(TAG,System.currentTimeMillis() + "：加载资源结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try{
                    BookInstance instance = new BookInstance(1);
                    for(int i=0;i<10;i++){
                        long start = System.currentTimeMillis();
                        String content = instance.getCurrentPageContent();
                        Log.w(FutureTaskDemo.TAG,"[1秒阅读时间]read:" + content);
                        Thread.sleep(1000);
                        Log.w(FutureTaskDemo.TAG,(System.currentTimeMillis() - start)+"");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
