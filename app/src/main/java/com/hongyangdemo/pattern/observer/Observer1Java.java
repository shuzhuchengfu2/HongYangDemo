package com.hongyangdemo.pattern.observer;

import android.util.Log;

import com.hongyangdemo.pattern.ObserverPattern;
import com.hongyangdemo.pattern.observer.SubjectFor3dJava;
import com.hongyangdemo.pattern.observer.SubjectForSSQJava;

import java.util.*;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe:观察者
 */

public class Observer1Java implements java.util.Observer {
    public void registerSubject(Observable observable){
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof SubjectFor3dJava){
            SubjectFor3dJava subjectFor3dJava = (SubjectFor3dJava) o;
            Log.d(ObserverPattern.TAG,"SubjectFor3dJava:"+subjectFor3dJava.getMsg());
        }

        if(o instanceof SubjectForSSQJava){
            SubjectForSSQJava subjectForSSQJava = (SubjectForSSQJava) o;
            Log.d(ObserverPattern.TAG,"SubjectForSSQJava:"+subjectForSSQJava.getMsg());
        }
    }
}
