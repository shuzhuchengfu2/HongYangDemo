package com.hongyangdemo.pattern.appearance;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:
 */

public class HomeTheaterFacade {
    private Computer computer;
    private Player player;
    private Light light;
    private Projector projector;
    private PopcornPopper popper;
    public HomeTheaterFacade(Computer computer,Player player,Light light,Projector projector,
                             PopcornPopper popcornPopper){
        this.computer = computer;
        this.player = player;
        this.light = light;
        this.projector = projector;
        this.popper = popcornPopper;
    }

    /**
     * 看电影
     */
    public void watchMovie()
    {
        /**
         *  1、打开爆米花机
         2、制作爆米花
         3、将灯光调暗
         4、打开投影仪
         5、放下投影仪投影区
         6、打开电脑
         7、打开播放器
         8、将播放器音调设为环绕立体声
         */
        popper.on();
        popper.makePopcorn();
        light.down();
        projector.on();
        projector.open();
        computer.on();
        player.on();
        player.make3DListener();
    }
}
