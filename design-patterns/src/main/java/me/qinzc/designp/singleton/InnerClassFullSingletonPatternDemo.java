package me.qinzc.designp.singleton;

/**
 * desc : 基于内部类的 线程安全的饱汉单例模式
 *
 * 这个是我们实际开发中，最稳妥的单例模式
 * @author Zane Qin
 * creatTime : 11:12 2021/11/18
 * modifier:
 * modifyTime:
 */
public class InnerClassFullSingletonPatternDemo {

    /**
     * 可以做到饱汉
     * 内部类，只要没有被使用，就不会初始化，singleton的实例就不会创建
     * 在第一次有人调用getInstance方法的时候，内部类会被初始化，创建一个singleton实例
     * java能确保的是，类静态初始化的过程一定只会执行一次。保证了单例
     */
    public static class Singleton{
        private Singleton() {

        }

        public static class InnerHolder{
            public static final Singleton instance = new Singleton();
        }

        public static Singleton getInstance() {
            return InnerHolder.instance;
        }
    }
}
