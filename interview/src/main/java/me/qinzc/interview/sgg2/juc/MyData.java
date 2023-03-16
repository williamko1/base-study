package me.qinzc.interview.sgg2.juc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc :
 *
 * @author : Zane Qin
 * createTime : 21:15 2023/3/16
 */
public class MyData{
    volatile int i = 0;
    AtomicInteger myInteger = new AtomicInteger(0);
    public void addTo60() {
        this.i = 60;
    }

    public void addPlusPlus() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();
    }

    public void addAtomic() {
        myInteger.incrementAndGet();
    }
}