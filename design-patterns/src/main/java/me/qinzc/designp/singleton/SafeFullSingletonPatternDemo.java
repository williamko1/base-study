package me.qinzc.designp.singleton;

/**
 * desc : 线程安全的饿汉单例模式
 *
 * @author Zane Qin
 * creatTime : 11:03 2021/11/18
 * modifier:
 * modifyTime:
 */
public class SafeFullSingletonPatternDemo {

    public static class Singleton{

        public static Singleton instance;

        private Singleton() {
        }

        /**
         * 不是完美的
         * 因为不同的jvm编译器问题，可能导致还是线程不安全的
         * @return
         */
        public static Singleton getInstance() {
            /**
             * double check
             */
            // 1.如果线程1和线程2都执行到了这一步，线程1判断发现是null
            // 2.切线程2，发现也是null，切线程1
            if (instance == null) {
                // 3. 线程1获取到锁
                // 4. 切线程2， 线程2尝试获取锁，发现锁被其他线程持有，等待释放，切换线程1
                // 7. 线程2 获取到锁
                synchronized (SafeFullSingletonPatternDemo.class) {
                    // 5.线程1发现是null，执行 new
                    // 8.判断instance != null, 跳出，释放锁， 这里是要double check的原因。防止多例
                    if (instance == null) {
                        return new Singleton();
                    }
                }
            }
            // 6. 线程1返回instance，释放锁
            return instance;
        }
    }


}
