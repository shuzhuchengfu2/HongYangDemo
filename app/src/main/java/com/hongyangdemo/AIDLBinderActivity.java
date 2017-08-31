package com.hongyangdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhy.calc.aidl.ICalcAIDL;
import com.zhy.calc.aidl.IMyAidlInterface;
import com.zhy.calc.aidl.Person;

public class AIDLBinderActivity extends AppCompatActivity {
    private ICalcAIDL mCalcAidl;
    private ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("client", "onServiceConnected");
            mCalcAidl = ICalcAIDL.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("client", "onServiceDisconnected");
            mCalcAidl = null;
        }
    };

    private IMyAidlInterface iMyAidlInterface;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface =IMyAidlInterface.Stub.asInterface(service);
            Log.d("client", "MyService onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlInterface = null;
            Log.d("client", "MyService onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidlbinder);

    }

    /**
     * 点击BindService按钮时调用
     *
     * @param view
     */
    public void bindService(View view) {
        Intent intent = new Intent();
        intent.setAction("com.zhy.aidl.calc");
        //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
        intent.setPackage("com.zhy.calc.aidl");
        bindService(intent, mServiceConn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 点击unBindService按钮时调用
     *
     * @param view
     */
    public void unbindService(View view) {
        unbindService(mServiceConn);
    }

    /**
     * 点击12+12按钮时调用
     *
     * @param view
     */
    public void addInvoked(View view) throws Exception {
        if (mCalcAidl != null) {
            int addRes = mCalcAidl.add(12, 12);
            Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "服务器被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 点击50-12按钮时调用
     *
     * @param view
     */
    public void minInvoked(View view) throws Exception {

        if (mCalcAidl != null) {
            int addRes = mCalcAidl.min(58, 12);
            Toast.makeText(this, addRes + "", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "服务端未绑定或被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void bindMyService(View view){
        Intent intent = new Intent();
        intent.setAction("com.zhy.aidl.person");
        //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
        intent.setPackage("com.zhy.calc.aidl");
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }


    public void getServiceValue(View view){
        Log.d("client","getServiceValue");
        if (iMyAidlInterface != null) {
            try{
                Person person = iMyAidlInterface.getValue();
//                Toast.makeText(this,person.toString(),Toast.LENGTH_LONG);
                Log.d("client",person.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(this, "服务端未绑定或被异常杀死，请重新绑定服务端", Toast.LENGTH_SHORT).show();
        }
    }
}
