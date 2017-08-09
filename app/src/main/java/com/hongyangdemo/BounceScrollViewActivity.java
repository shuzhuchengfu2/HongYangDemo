package com.hongyangdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hongyangdemo.widget.BounceScrollView;

import java.util.ArrayList;
import java.util.Arrays;

public class BounceScrollViewActivity extends AppCompatActivity {
    private BounceScrollView mScrollView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce_scroll_view);
        mScrollView = (BounceScrollView) findViewById(R.id.id_scrollView);
        mScrollView.setCallBack(new BounceScrollView.Callback() {
            @Override
            public void callback() {
                Toast.makeText(BounceScrollViewActivity.this,
                        "you can do something!", Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(MainActivity.this,
//                        SecondActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        mListView = (ListView) findViewById(R.id.id_listView);
        mListView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new ArrayList<>(
                Arrays.asList("Hello", "World", "Welcome", "Java",
                        "Android", "Lucene", "C++", "C#", "HTML",
                        "Welcome", "Java", "Android", "Lucene", "C++",
                        "C#", "HTML"))));
    }
}
