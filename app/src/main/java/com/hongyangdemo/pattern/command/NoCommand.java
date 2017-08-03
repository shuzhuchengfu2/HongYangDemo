package com.hongyangdemo.pattern.command;

import android.util.Log;

import com.hongyangdemo.pattern.CommandPattern;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:
 */

public class NoCommand implements Command {
    @Override
    public void execute() {
        Log.d(CommandPattern.TAG,"没有命令");
    }
}
