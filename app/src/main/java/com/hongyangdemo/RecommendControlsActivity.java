package com.hongyangdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RecommendControlsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_controls);
    }

    /**
     * DialogFragment
     * @param view
     */
    public void dialogFragment(View view){
        Intent intent = new Intent(this, DialogFragmentActivity.class);
        startActivity(intent);
    }
}
