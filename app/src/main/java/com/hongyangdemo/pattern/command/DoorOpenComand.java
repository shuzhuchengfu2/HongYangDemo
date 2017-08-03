package com.hongyangdemo.pattern.command;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:
 */

public class DoorOpenComand implements Command {
    public Door door;
    public DoorOpenComand(Door door){
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }
}
