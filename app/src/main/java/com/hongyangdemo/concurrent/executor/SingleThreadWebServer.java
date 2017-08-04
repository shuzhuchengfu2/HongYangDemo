package com.hongyangdemo.concurrent.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe: 单线程服务器
 */

public class SingleThreadWebServer {
    public SingleThreadWebServer() throws IOException {
        ServerSocket socket = new ServerSocket(1111);
        while (true) {
            Socket client = socket.accept();
            handleReq(client);
        }
    }

    /**
     * 处理请求
     *
     * @param client
     */
    private void handleReq(Socket client) {
    }
}
