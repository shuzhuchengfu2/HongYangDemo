package com.hongyangdemo.pattern;

import com.hongyangdemo.pattern.appearance.Computer;
import com.hongyangdemo.pattern.appearance.HomeTheaterFacade;
import com.hongyangdemo.pattern.appearance.Light;
import com.hongyangdemo.pattern.appearance.Player;
import com.hongyangdemo.pattern.appearance.PopcornPopper;
import com.hongyangdemo.pattern.appearance.Projector;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe:外观模式
 */

public class AppearancePattern {
    public static final String TAG = "AppearancePattern";
    /**
     * 提供一个统一的接口用来访问子系统中的一群接口，外观定义了一个高层的接口，让子系统更容易使用
     */
    /**
     * 看电影：投影仪，电脑，音响
     */

    public void testAppearancePattern(){
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(new Computer(),new Player(),new Light(),new Projector(),new PopcornPopper());
        homeTheaterFacade.watchMovie();
    }
}
