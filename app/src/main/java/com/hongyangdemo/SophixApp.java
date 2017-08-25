package com.hongyangdemo;

import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * author： xiongdejin
 * date: 2017/8/17
 * describe:
 */

public class SophixApp extends Application {
    public static final String TAG = "SophixApp";
    @Override
    public void onCreate() {
        super.onCreate();
        initSophix();
        initARouter();


    }


    private void initARouter(){
        if(BuildConfig.DEBUG){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private void initSophix() {
        String appVersion;

        try {
            appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = "1.0.0";
            e.printStackTrace();
        }

        // initialize最好放在attachBaseContext最前面
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Log.d(TAG,"CODE_LOAD_SUCCESS");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                            Log.d(TAG,"CODE_LOAD_RELAUNCH");
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            Log.d(TAG,"CODE_ERROR code:"+code);
                        }
                    }
                }).initialize();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
