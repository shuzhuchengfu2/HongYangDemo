package com.hongyangdemo.pattern.bean;

import android.util.Log;

import com.hongyangdemo.pattern.StrategyPattern;
import com.hongyangdemo.pattern.bean.impl.DisplayInterface;
import com.hongyangdemo.pattern.bean.impl.RunInterface;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 游戏角色的超类
 */

public abstract class Role {
    protected String name;

    public Role(String name){
        this.name = name;
        Log.d(StrategyPattern.TAG,this.getClass().getSimpleName()+"=====>");
    }

    private DisplayInterface displayInterface;

    /**
     * 设置表现的样子
     * @param displayInterface
     */
    public void setDisplayInterface(DisplayInterface displayInterface){
        this.displayInterface = displayInterface;
    }

    private RunInterface runInterface;

    /**
     * 设置逃跑的技能
     * @param runInterface
     */
    public void setRunInterface(RunInterface runInterface){
        this.runInterface = runInterface;
    }

    /**
     * 秀一下
     */
    public void expression(){
        if(displayInterface == null){
            Log.d(StrategyPattern.TAG,"没脸啦！");
        }else{
            displayInterface.display();
        }

        if(runInterface == null){
            Log.d(StrategyPattern.TAG,"勇往直前！");
        }else{
            runInterface.run();
        }
    }

}
