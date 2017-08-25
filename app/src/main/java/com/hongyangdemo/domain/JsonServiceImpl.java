package com.hongyangdemo.domain;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.fastjson.JSON;

/**
 * author： xiongdejin
 * date: 2017/8/25
 * describe:
 */
@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {
    @Override
    public <T> T json2Object(String json, Class<T> clazz) {
        return JSON.parseObject(json,clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return JSON.toJSONString(instance);
    }

    @Override
    public void init(Context context) {

    }
}
