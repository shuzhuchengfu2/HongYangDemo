package com.hongyangdemo.pattern;

import com.hongyangdemo.pattern.bean.Role;
import com.hongyangdemo.pattern.bean.impl.DisplayA;
import com.hongyangdemo.pattern.bean.impl.DisplayB;
import com.hongyangdemo.pattern.bean.impl.RunA;

/**
 * author： xiongdejin
 * date: 2017/8/1
 * describe: 设计模式之策略模式(以角色游戏为背景)
 */

public class StrategyPattern {
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
        RoleB b = new RoleB();
        a.expression();
        b.expression();
    }
}
