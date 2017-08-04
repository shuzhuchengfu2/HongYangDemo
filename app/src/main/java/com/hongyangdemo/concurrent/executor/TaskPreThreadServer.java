package com.hongyangdemo.concurrent.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:为每个任务分配一个线程、
 * 1.线程的生命周期开销还是相当大的，
 * 2.线程量过大可能活出现OOM的问题
 * 3.线程过大可能会降低程序运行的速度
 */

public class TaskPreThreadServer {
    public TaskPreThreadServer() throws IOException {
        ServerSocket socket = new ServerSocket(1111);
        while (true) {
            final Socket client = socket.accept();
            new Thread(){
                @Override
                public void run() {
                    handleReq(client);
                }
            }.start();
        }
    }

    protected void handleReq(Socket client) {

    }
}
