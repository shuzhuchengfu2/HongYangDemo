package com.hongyangdemo.network.http;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:
 */

public class DownloadThread extends Thread {
    /**
     * 下载文件的URI
     */
    private URL url;
    /**
     * 存的本地路径
     */
    private File localFile;
    /**
     * 是否结束
     */
    private boolean isFinish;
    /**
     * 开始的位置
     */
    private Long startPos;
    /**
     * 结束位置
     */
    private Long endPos;

    public DownloadThread(URL url, File savefile, Long startPos, Long endPos) {
        this.url = url;
        this.localFile = savefile;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {
        Log.d(MultipartThreadDownloador.TAG, "开始下载...");
        try {
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setConnectTimeout(15 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty(
                    "Accept",
                    "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
            conn.setRequestProperty("Accept-Language", "zh-CN");
            conn.setRequestProperty("Referer", url.toString());
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Range", "bytes=" + startPos + "-"
                    + endPos);// 设置获取实体数据的范围

            conn.setRequestProperty(
                    "User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.connect();
            /**
             * 代表服务器已经成功处理了部分GET请求
             */
            if (conn.getResponseCode() == 206) {
                InputStream is = conn.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                int progress = 0;

                RandomAccessFile raf = new RandomAccessFile(localFile,"rwd");
                raf.seek(startPos);
                while ((len = is.read(buf)) != -1) {
                    raf.write(buf, 0, len);
                    progress += len;
                    Log.d(MultipartThreadDownloador.TAG, Thread.currentThread().getName()
                            + "下载中  ： " + (int)(progress*100f /(endPos - startPos))+"%");
                }
                raf.close();
                is.close();
                Log.d(MultipartThreadDownloador.TAG, Thread.currentThread().getName()
                        + "完成下载  ： " + startPos + " -- " + endPos);
                this.isFinish = true;
            }
            else {
                throw new RuntimeException(
                        "url that you conneted has error ...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
