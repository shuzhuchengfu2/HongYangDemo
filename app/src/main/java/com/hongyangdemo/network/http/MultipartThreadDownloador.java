package com.hongyangdemo.network.http;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author： xiongdejin
 * date: 2017/8/4
 * describe:多线程下载
 */

public class MultipartThreadDownloador {
    public static final String TAG = "Multipart";
    /**
     * 需要下载资源的地址
     */
    private String urlStr;
    /**
     * 下载的文件
     */
    private File localFile;
    /**
     * 需要下载文件的存放的本地文件夹路径
     */
    private String dirStr;
    /**
     * 存储到本地的文件名
     */
    private String filename;

    /**
     * 开启的线程数量
     */
    private int threadCount;
    /**
     * 下载文件的大小
     */
    private long fileSize;

    /**
     * @param urlStr      需要下载资源的地址
     * @param dirStr      需要下载文件的存放的本地文件夹路径
     * @param filename    存储到本地的文件名
     * @param threadCount 开启的线程数量
     */
    public MultipartThreadDownloador(String urlStr, String dirStr, String filename, int threadCount) {
        this.urlStr = urlStr;
        this.dirStr = dirStr;
        this.filename = filename;
        this.threadCount = threadCount;
    }

    /**
     * 根据资源的URL获取资源的大小，以及在本地创建文件
     *
     * @throws IOException
     */
    public void createFileByUrl() throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(15 * 1000);
        conn.setRequestMethod("GET");
        //设置请求头
        conn.setRequestProperty(
                "Accept",
                "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Referer", urlStr);
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty(
                "User-Agent",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.connect();
        if (conn.getResponseCode() == 200) {
            this.fileSize = conn.getContentLength();// 根据响应获取文件大小
            if (fileSize <= 0)
                throw new RuntimeException("the file that you download has a wrong size ... ");
            File dir = new File(dirStr);
            if (!dir.exists())
                dir.mkdirs();
            this.localFile = new File(dir, filename);
            RandomAccessFile raf = new RandomAccessFile(this.localFile, "rw");
            raf.setLength(fileSize);
            raf.close();
            Log.d(TAG, "需要下载的文件大小为 :" + this.fileSize + " , 存储位置为： " + dirStr + "/" + filename);
        }
        else {
            throw new RuntimeException("url that you conneted has error ...");
        }
    }

    public void download() throws IOException {
        new Thread(){
            @Override
            public void run() {
                try{
                    createFileByUrl();
                    //计算每个线程需要下载的数据长度
                    long block = fileSize % threadCount == 0 ? fileSize / threadCount
                            : fileSize / threadCount + 1;
                    for (int i = 0; i < threadCount; i++) {
                        long start = i * block;
                        long end = start + block >= fileSize ? fileSize : start + block - 1;
                        new DownloadThread(new URL(urlStr), localFile, start, end).start();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
