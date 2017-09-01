package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SourceCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source_code);
    }

    /**
     *  Android AsyncTask 源码解析
     * @param view
     */
    public void asyncTask(View view){
        Intent intent = new Intent(this,AsyncTaskActivity.class);
        startActivity(intent);
    }
}
