package com.hongyangdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Android AsyncTask 源码解析
 */
public class AsyncTaskActivity extends AppCompatActivity {
    public static final String TAG = "AsyncTaskActivity";
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
    }

    /**
     * 开始加载
     *
     * @param view
     */
    public void startLoading(View view) {
        mDialog = new ProgressDialog(this);
        mDialog.setMax(100);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setCancelable(false);
        new MyAsyncTask().execute("张三");
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog.show();
            Log.d(TAG, Thread.currentThread().getName() + " onPreExecute ");
        }

        @Override
        protected Void doInBackground(String... params) {
            // 模拟数据的加载,耗时的任务
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //调用update的方法
                publishProgress(i);
            }
            Log.d(TAG, "params:"+params[0]);
            Log.d(TAG, Thread.currentThread().getName() + " doInBackground ");
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mDialog.setProgress(values[0]);
            Log.d(TAG, Thread.currentThread().getName() + " onProgressUpdate ");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // 进行数据加载完成后的UI操作
            mDialog.dismiss();
            Toast.makeText(AsyncTaskActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
            Log.d(TAG, Thread.currentThread().getName() + " onPostExecute ");
        }
    }
}
