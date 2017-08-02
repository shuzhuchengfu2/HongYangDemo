package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }

    /**
     * Volley
     * @param view
     */
    public void volley(View view){
        Intent intent = new Intent(this,VolleyActivity.class);
        startActivity(intent);
    }
}
