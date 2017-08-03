package com.hongyangdemo.pattern.command;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:
 */

public class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.off();
    }
}
