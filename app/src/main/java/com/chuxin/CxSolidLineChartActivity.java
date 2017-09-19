package com.chuxin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chuxin.widget.CxSolidLineChart;
import com.hongyangdemo.R;

import java.util.Random;

public class CxSolidLineChartActivity extends AppCompatActivity {
    private CxSolidLineChart cslc_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cx_solid_line_chart);
        cslc_index = (CxSolidLineChart) findViewById(R.id.cslc_index);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cslc_index.setData(6.8f,10.0f,0.4f);
        cslc_index.setCurrentMonth(new Random().nextInt(5));
    }
}
