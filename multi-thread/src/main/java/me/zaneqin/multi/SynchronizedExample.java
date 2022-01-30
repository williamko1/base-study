package me.zaneqin.multi;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 14:07 2022/1/29
 * modifier:
 * modifyTime:
 */
public class SynchronizedExample {
    private int x =0;

    public void synBlock() {
        // 1 加锁
        synchronized (SynchronizedExample.class) {
            x = 1;// 赋值
        }
        // 3 解锁
    }

    // 1.加锁
    public synchronized void synMethod() {
        x = 2; // 赋值
    }
    // 2.解锁
}
