package com.hongyangdemo.pattern;

import android.util.Log;

import com.hongyangdemo.pattern.command.ControlPanel;
import com.hongyangdemo.pattern.command.Door;
import com.hongyangdemo.pattern.command.DoorCloseComand;
import com.hongyangdemo.pattern.command.DoorOpenComand;
import com.hongyangdemo.pattern.command.Light;
import com.hongyangdemo.pattern.command.LightOffCommand;
import com.hongyangdemo.pattern.command.LightOnCommand;
import com.hongyangdemo.pattern.command.NoCommand;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:命令模式
 */

public class CommandPattern {
    public static final String TAG = "CommandPattern";
    public void testCommandPattern(){
        /**
         * 将请求封装成对象，将动作请求者和动作执行者解耦
         */
        Light light = new Light();
        Door door = new Door();

        //配置功能
        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setCommand(0,new NoCommand());
        controlPanel.setCommand(1,new DoorOpenComand(door));
        controlPanel.setCommand(2,new DoorCloseComand(door));
        controlPanel.setCommand(3,new NoCommand());
        controlPanel.setCommand(4,new LightOnCommand(light));
        controlPanel.setCommand(5,new LightOffCommand(light));

        Log.d(TAG,"CommandPattern 点击==>");
        //模拟点击
        controlPanel.keyPressed(0);
        controlPanel.keyPressed(1);
        controlPanel.keyPressed(2);
        controlPanel.keyPressed(3);
        controlPanel.keyPressed(4);
        controlPanel.keyPressed(5);

    }

}

