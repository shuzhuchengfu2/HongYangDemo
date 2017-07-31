package com.hongyangdemo.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hongyangdemo.R;

public class MyImageActivity extends AppCompatActivity {
    MyImageView joke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_image);
        joke = (MyImageView) findViewById(R.id.c_joke);
        joke.setOnClickIntent(new MyImageView.OnViewClickListener(){

            @Override
            public void onViewClick(MyImageView view){
                Toast.makeText(MyImageActivity.this, "Joke", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
