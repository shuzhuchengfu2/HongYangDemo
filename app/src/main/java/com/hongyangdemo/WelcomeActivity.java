package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hongyangdemo.widget.VerticalLinearLayout;

public class WelcomeActivity extends AppCompatActivity {
    private VerticalLinearLayout mMianLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mMianLayout = (VerticalLinearLayout) findViewById(R.id.id_main_ly);
        mMianLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener() {
            @Override
            public void onPageChange(int currentPage){
                Toast.makeText(WelcomeActivity.this, "第"+(currentPage+1)+"页", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void toMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
