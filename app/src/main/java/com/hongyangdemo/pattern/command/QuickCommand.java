package com.hongyangdemo.pattern.command;

/**
 * author： xiongdejin
 * date: 2017/8/3
 * describe: 一键操作命令
 */

public class QuickCommand implements Command {
    private Command[] commands;

    public QuickCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        if (commands == null) return;
        for (Command command : commands) {
            command.execute();
        }
    }
}
