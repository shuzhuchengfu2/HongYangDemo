package com.hongyangdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hongyangdemo.network.JsonRequestWithAuth;
import com.hongyangdemo.network.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity {
    public static final String TAG = "VolleyActivity";
    private ImageView iv_volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        iv_volley = (ImageView) findViewById(R.id.iv_volley);
    }

    /**
     * Volley Get方法
     *
     * @param view
     */
    public void volleyGet(View view) {
        // 1.创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("http://japi.juhe.cn/joke/content/list.from",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage(), error);
                    }
                });
        requestQueue.add(stringRequest);
    }

    /**
     * Volley 图片
     *
     * @param view
     */
    public void volleyImage(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(
                "https://avatars2.githubusercontent.com/u/17371027?v=4&u=817cf662b23257b1cfce94b8e2aabebe3edd9b07&s=400",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        iv_volley.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, null
        );
        requestQueue.add(imageRequest);
    }

    /**
     * Volley JsonObject
     *
     * @param view
     */
    public void volleyJson(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://japi.juhe.cn/joke/content/list.from",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, null);
        requestQueue.add(jsonObjectRequest);
    }

    /**
     * 自定义request
     *
     * @param view
     */
    public void volleyWithAuth(View view) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Map<String, String> appendHeader = new HashMap<String, String>();
        appendHeader.put("username", "admin");
        appendHeader.put("password", "123");
        String url = "http://172.27.35.1:8080/webTest/TestServlet";
        JsonRequestWithAuth<User> userRequest = new JsonRequestWithAuth<>(url, null, User.class,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        Log.d(TAG, response.toString());
                    }
                }, appendHeader);
        requestQueue.add(userRequest);
    }
}
