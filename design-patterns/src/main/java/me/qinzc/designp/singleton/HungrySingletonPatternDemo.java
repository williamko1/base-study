package me.qinzc.designp.singleton;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 10:54 2021/11/18
 * modifier:
 * modifyTime:
 */
public class HungrySingletonPatternDemo {

    public static void main(String[] args) {
        Singleton.getInstance().execute();
    }

    public static class Singleton {
        /**
         * 第一步：直接就是将这个类的实例创建出来,赋予static final修饰的变量
         */
        private static final Singleton instance = new Singleton();

        /**
         * 第二步：私有构造方法（防止多例）
         */
        private Singleton() {
        }

        /**
         * 第三部：提供公有静态获取实例的方法
         * @return
         */
        public static Singleton getInstance() {
            return instance;
        }

        public void execute() {
            System.out.println("饿汉单例的执行方法");
        }
    }
}

