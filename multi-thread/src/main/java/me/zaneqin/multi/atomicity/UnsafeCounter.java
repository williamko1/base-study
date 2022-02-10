package me.zaneqin.multi.atomicity;

/**
 * desc : counter的锁是this
 * calc的锁是UnsafeCounter.class
 *
 * @author Zane Qin
 * creatTime : 10:39 2022/2/7
 * modifier:
 * modifyTime:
 */
public class UnsafeCounter {

    private static int count;


    public synchronized void counter() {
        count++;
    }

    public static synchronized int calc() {
        return count++;
    }
}
