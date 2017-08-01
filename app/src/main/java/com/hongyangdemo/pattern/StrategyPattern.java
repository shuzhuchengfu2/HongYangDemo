package com.hongyangdemo.pattern;

import com.hongyangdemo.pattern.strategy.Role;
import com.hongyangdemo.pattern.strategy.DisplayA;
import com.hongyangdemo.pattern.strategy.DisplayB;
import com.hongyangdemo.pattern.strategy.RunA;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 设计模式之策略模式(以角色游戏为背景)
 *
 * 策略模式是对算法的包装，是把使用算法的责任和算法本身分割开来，委派给不同的对象管理。
 * 策略模式通常把一个系列的算法包装到一系列的策略类里面，作为一个抽象策略类的子类。
 */

public class StrategyPattern {
    public static String TAG = "StrategyPattern";

    /**
     * 角色分析：1.姓名设置 唯一 2.逃跑，攻击，防御 技能都是可以重复的
     *
     * 对于每个角色都可以统一实现这些方法
     *
     * 逃跑，攻击，防御 都可以抽象 并具体实现
     *
     * 对于角色可以提供设置这些技能的方法
     *
     */

    public class RoleA extends Role{
        public RoleA() {
            super("角色A");
            this.setDisplayInterface(new DisplayA());
            this.setRunInterface(new RunA());
        }
    }

    public class RoleB extends Role{
        public RoleB() {
            super("角色B");
            this.setDisplayInterface(new DisplayB());
            this.setRunInterface(new RunA());
        }
    }

    /**
     * 测试角色
     */
    public void testRole(){

        RoleA a = new RoleA();
        a.expression();

        RoleB b = new RoleB();
        b.expression();
    }
}
