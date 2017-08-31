package com.zhy.calc.aidl;

import android.os.RemoteException;
import android.util.Log;

/**
 * author： xiongdejin
 * date: 2017/8/31
 * describe:
 */

public class IMyService extends IMyAidlInterface.Stub {
    @Override
    public Person getValue() throws RemoteException {
        Log.d("server","===============");
        return new Person("Hello");
    }
}
