package com.hongyangdemo.concurrent.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:基于Executor的服务器
 */

public class TaskExecutionServer {
    private static final int THREAD_COUNT = 100;
    private static final Executor exec = Executors.newFixedThreadPool(THREAD_COUNT);
    public TaskExecutionServer() throws IOException {
        ServerSocket socket = new ServerSocket(1111);
        while(true){
            final Socket client = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleReq(client);
                }
            };
            exec.execute(task);
        }
    }

    protected void handleReq(Socket client) {

    }
}
