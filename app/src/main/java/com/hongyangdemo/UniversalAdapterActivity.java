package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.hongyangdemo.others.CommonAdapter;
import com.hongyangdemo.others.MyAdapter;
import com.hongyangdemo.others.ViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Android 快速开发系列 打造万能的ListView GridView 适配器
 */
public class UniversalAdapterActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mDatas = new ArrayList<>(Arrays.asList("Hello", "World", "Welcome"));
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal_adapter);
        mListView = (ListView) findViewById(R.id.id_lv_main);
//        mListView.setAdapter(mAdapter = new MyAdapter(this, mDatas));
        mListView.setAdapter(new CommonAdapter<String>(this,mDatas,R.layout.item_single_str) {
            @Override
            public void convert(ViewHolder helper, String item) {
                ((TextView)helper.getView(R.id.id_tv_title)).setText(item);
            }
        });
    }
}
