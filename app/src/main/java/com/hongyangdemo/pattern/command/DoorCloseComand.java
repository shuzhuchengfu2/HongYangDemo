package com.hongyangdemo.pattern.command;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:
 */

public class DoorCloseComand implements Command{
    private  Door door;
    public DoorCloseComand(Door door){
        this.door = door;
    }


    @Override
    public void execute() {
        door.close();
    }
}
