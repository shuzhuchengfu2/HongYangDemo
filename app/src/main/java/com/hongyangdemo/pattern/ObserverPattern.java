package com.hongyangdemo.pattern;

import android.util.Log;

import com.hongyangdemo.pattern.observer.ObjectFor3D;
import com.hongyangdemo.pattern.observer.SubjectFor3dJava;
import com.hongyangdemo.pattern.observer.SubjectForSSQJava;
import com.hongyangdemo.pattern.observer.Observer1;
import com.hongyangdemo.pattern.observer.Observer1Java;
import com.hongyangdemo.pattern.observer.Observer2;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:  设计模式 观察者模式 以微信公众服务为例
 */

public class ObserverPattern {
    public static final String TAG = "ObserverPattern";
    /**
     * 1、服务号就是主题，业务就是推送消息
     * 2、观察者只需要订阅主题，只要有新的消息就会送来
     * 3、当不想要此主题消息时，取消订阅
     * 4、只要服务号还在，就会一直有人订阅
     */

    public void testObserverPattern(){
        ObjectFor3D objectFor3D = new ObjectFor3D();
        Observer1 observer1 = new Observer1(objectFor3D);
        Observer2 observer2 = new Observer2(objectFor3D);

        //123
        objectFor3D.setMsg("20140420的3D号码是：127");
        Log.i(TAG,"=====================");
        objectFor3D.removeObserver(observer1);
        objectFor3D.setMsg("20140421的3D号码是：000");

        Log.i(TAG,"===========JAVA==========");

        SubjectFor3dJava subjectFor3dJava = new SubjectFor3dJava();
        SubjectForSSQJava subjectForSSQJava = new SubjectForSSQJava();

        Observer1Java observer1Java = new Observer1Java();
        observer1Java.registerSubject(subjectFor3dJava);
        observer1Java.registerSubject(subjectForSSQJava);

        subjectFor3dJava.setMsg("123");
        subjectForSSQJava.setMsg("456");

    }



}
