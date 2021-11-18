package me.qinzc.designp.singleton;

/**
 * desc : 线程不安全的饱汉单例模式
 *
 * @author Zane Qin
 * creatTime : 11:00 2021/11/18
 * modifier:
 * modifyTime:
 */
public class UnsafeFullSingletonPatternDemo {

    /**
     * 线程不安全的
     *
     */
    public static class Singleton {
        private static Singleton instance;

        private Singleton() {
        }

        public static Singleton getInstance() {
            /**
             * 假设有2个线程过来，同时判断是null，都执行了new()
             */
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}
