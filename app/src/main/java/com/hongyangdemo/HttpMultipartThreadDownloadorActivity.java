package com.hongyangdemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.network.http.MultipartThreadDownloador;

import java.io.IOException;

/**
 * 基于http的多线程下载
 */
public class HttpMultipartThreadDownloadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_multipart_thread_downloador);
        // http://file.chuxinlicai.com/resource/download/app2_0/chuxinlicai_350_jiagu_sign.apk

    }

    /**
     * 下载
     *
     * @param view
     */
    public void down(View view) {
        //TODO 没有兼容6.0权限问题
        try {
            new MultipartThreadDownloador("http://file.chuxinlicai.com/resource/download/app2_0/chuxinlicai_350_jiagu_sign.apk",
                    Environment.getExternalStorageDirectory() + "/HONGYANG", "chuxinlicai.apk", 6).download();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
