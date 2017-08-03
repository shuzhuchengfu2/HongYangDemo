package com.hongyangdemo.pattern.command;

import android.util.Log;

import com.hongyangdemo.pattern.CommandPattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:控制板
 */

public class ControlPanel {
    private Command[] commands = new Command[6];

    public ControlPanel() {
        Log.d(CommandPattern.TAG, "ControlPanel 初始化==>");
        for (int i = 0; i < 6; i++) {
            commands[i] = new NoCommand();
            commands[i].execute();
        }
    }


    public Command [] getCommands(){
        return commands;
    }

    /**
     * 设置每个按钮对应的命令
     *
     * @param index
     * @param command
     */
    public void setCommand(int index, Command command) {
        commands[index] = command;
    }

    /**
     * 模拟点击按钮
     *
     * @param index
     */
    public void keyPressed(int index) {
        commands[index].execute();
    }

}
