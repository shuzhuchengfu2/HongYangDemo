package com.hongyangdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hongyangdemo.widget.ClipImageBorderView;

import java.io.ByteArrayOutputStream;

public class ClipImageBorderViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_image_border_view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void clip(View view){
        Bitmap bitmap = ((ClipImageBorderView)findViewById(R.id.id_clipImageLayout)).clip();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] datas = baos.toByteArray();
        view.setBackground(new BitmapDrawable(bitmap));
    }
}
