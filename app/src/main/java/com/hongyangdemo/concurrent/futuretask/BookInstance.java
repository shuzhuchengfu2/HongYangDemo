package com.hongyangdemo.concurrent.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:模拟用户查看电子书
 */

public class BookInstance {
    private volatile int currentPage = 0;

    /**
     * 根据页码从网络抓取数据
     *
     * @return
     * @throws InterruptedException
     */
    private String loadDataFromNet() throws InterruptedException {
        Thread.sleep(1000);
        return "Page " + this.currentPage + " : the content ....";
    }

    FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
        @Override
        public String call() throws Exception {
            return loadDataFromNet();
        }
    });

    /**
     * 实例化一本书，并传入当前读到的页码
     * @param currentPage
     */
    public BookInstance(int currentPage){
        this.currentPage = currentPage;
        Thread thread = new Thread(futureTask);
        thread.start();
    }

    /**
     * 获取当前页的内容
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public String getCurrentPageContent() throws InterruptedException,
            ExecutionException {
        String con = futureTask.get();
        this.currentPage = this.currentPage +1;
        Thread thread = new Thread(futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return loadDataFromNet();
            }
        }));
        thread.start();
        return con;
    }
}
