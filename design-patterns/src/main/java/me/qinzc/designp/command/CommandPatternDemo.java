package me.qinzc.designp.command;

/**
 * desc :
 * 有2种请求要求发送过来执行
 * 一种请求是读请求，一种请求是写请求，不通请要执行的功能逻辑是不一样的
 * 此时就非常适合这种命令模式
 * 将读请求封装到readCommand，写请求封装到writeCommand
 * 然后设置一个通用的命令执行的类
 * 读请求来了，就封装ReadCommand，交给同一个命令执行类来执行
 * 写请求来了，就封装WriteCommand
 *
 * 很多时候 用模式，是采用模式的合理的想法，去良好的设计你的代码
 * 运用了模式之后，就可以很好的表达你的代码组件是干嘛的
 *
 *
 * @author Zane Qin
 * creatTime : 14:25 2021/12/3
 * modifier:
 * modifyTime:
 */
public class CommandPatternDemo {

    public static void main(String[] args) {
        Command commandA = new CommandA();
        Command commandB = new CommandB();
        Invoker invoker = new Invoker(commandA);
        invoker.execute();

        invoker.setCommand(commandB);
        invoker.execute();
    }

    public interface Command {

        void execute();
    }

    public static class CommandA implements Command {
        @Override
        public void execute() {
            System.out.println("命令A的功能逻辑");
        }
    }

    public static class CommandB implements Command {

        @Override
        public void execute() {
            System.out.println("命令B的功能逻辑");
        }
    }

    public static class Invoker {
        private Command command;

        public Invoker(Command command) {
            this.command = command;
        }
        public void execute() {
            command.execute();
        }

        public Command getCommand() {
            return command;
        }

        public void setCommand(Command command) {
            this.command = command;
        }
    }
}
