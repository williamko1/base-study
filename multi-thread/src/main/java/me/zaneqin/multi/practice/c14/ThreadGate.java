package me.zaneqin.multi.practice.c14;

/**
 * desc : 阀门类
 *
 * @author Zane Qin
 * creatTime : 11:08 2022/4/13
 * modifier:
 * modifyTime:
 */
public class ThreadGate {
    // 条件谓词: opened-since(n) (isOpen || generation>n)
    private boolean isOpen;
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }
    // 阻塞并知道: opened-since(generation on entry)
    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (!isOpen && arrivalGeneration == generation) {
            wait();
        }
    }
}
