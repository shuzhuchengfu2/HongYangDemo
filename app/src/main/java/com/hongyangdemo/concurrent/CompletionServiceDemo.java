package com.hongyangdemo.concurrent;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * author： xiongdejin
 * date: 2017/8/8
 * describe:
 */

public class CompletionServiceDemo {
    public static final String TAG = "CompletionServiceDemo";
    /**
     * 优势：
     * 1.阻塞队列，防止内存中排队等待的任务过多，导致内存溢出
     * 2.CompletionService可以实现，哪个任务先执行完成就返回，而不是按顺序返回，这样可以极大的提升效率
     */

    public void testCompletionServiceDemo(){
        demo1();
        testInvokeAll();
    }

    public void demo1(){
        //内部维护11个线程池
        ExecutorService service = Executors.newFixedThreadPool(11);
        //容量为10的阻塞队列
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<>(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(service,queue);
        //模拟瞬间执行10个任务，并且消耗时间不同
        for(int i=0;i<10;i++){
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int ran = new Random().nextInt(1000);
                    Thread.sleep(ran);
                    Log.d(TAG,Thread.currentThread().getName() + " 休息了 " + ran);
                    return ran;
                }
            });
        }

        for(int i=0;i<10;i++){
            try {
                Future<Integer> f = completionService.take();
                Log.d(TAG,f.get()+"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
    }


    public void testInvokeAll(){
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> tasks = new ArrayList<>();
        Callable task = null;
        for(int i=0;i<10;i++){
            task = new Callable() {
                @Override
                public Object call() throws Exception {
                    int ran = new Random().nextInt(1000);
                    Thread.sleep(ran);
                    Log.d(TAG,Thread.currentThread().getName()+" 休息了 " + ran );
                    return ran;
                }
            };
            tasks.add(task);
        }
        long s = System.currentTimeMillis();

        try {
            List<Future<Integer>> results = exec.invokeAll(tasks);
            Log.d(TAG,"执行任务消耗了 ："+ (System.currentTimeMillis() - s) +"毫秒");
            for (int i = 0; i < results.size(); i++){
                Log.d(TAG,results.get(i).get()+"");
            }
            exec.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
