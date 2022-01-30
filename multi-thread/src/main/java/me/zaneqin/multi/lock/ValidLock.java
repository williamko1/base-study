package me.zaneqin.multi.lock;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 10:41 2022/1/30
 * modifier:
 * modifyTime:
 */
public class ValidLock {
    private static final Object o = new Object();

    private int count;

    public synchronized void badSyn() {
        // 其他业务逻辑
        count++;
    }

    public void goodSyn() {
        // 其他业务逻辑

        synchronized (o) {
            count++;
        }
    }

}
