package me.zaneqin.multi.lock;

/**
 * desc : synchronized 的3种用法
 *
 * 对于普通同步⽅法，锁的是当前实例对象，通常指 this
 * 对于静态同步⽅法，锁的是当前类的 Class 对象，如 ThreeSync.class
 * 对于同步⽅法块，锁的是 synchronized 括号内的对象
 * @author Zane Qin
 * creatTime : 15:04 2022/1/29
 * modifier:
 * modifyTime:
 */
public class ThreeSyn {

    private static final Object o = new Object();

    public synchronized void normalSyncMethod() {
        // 临界区
    }

    public static synchronized void staticSyncMethod() {
        // 临界区
    }

    public void syncBlockMethod() {
        synchronized (o) {
            // 临界区
        }
    }
}
