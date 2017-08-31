package com.zhy.calc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * author： xiongdejin
 * date: 2017/8/31
 * describe:
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyService();
    }
}
