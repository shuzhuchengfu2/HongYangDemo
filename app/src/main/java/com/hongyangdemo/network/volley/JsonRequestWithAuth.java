package com.hongyangdemo.network.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * author： xiongdejin
 * date: 2017/8/2
 * describe: 自定义request
 */

public class JsonRequestWithAuth<T> extends Request<T> {
    private static Map<String, String> mHeader = new HashMap<String, String>();
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Response.Listener<T> listener;

    static {
        mHeader.put("APP-Key", "LBS-AAA");
        mHeader.put("APP-Secret", "ad12msa234das232in");
    }

    public JsonRequestWithAuth(String url, Response.ErrorListener errorListener,
    Class<T> clazz, Response.Listener<T> listener, Map<String, String> appendHeader) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
        mHeader.putAll(appendHeader);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeader;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            //得到返回的数据
            String jsonStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //转化成对象
            return Response.success(gson.fromJson(jsonStr,clazz),HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
