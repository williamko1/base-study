package me.zaneqin.multi.juc.practice;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 09:17 2022/2/24
 * modifier:
 * modifyTime:
 */
public interface Lock {

    /**
     * 阻塞式获取锁
     * 如果获取不到锁，当前线程在调度过程中变得不可用，在获取到锁之前都处于休眠
     */
    void lock();

    /**
     * 阻塞式获取锁，响应中断
     * 如果获取不到锁，当前线程在调度过程中变得不可用，在以下2种情况发生前一直处于休眠状态
     * 1. 获取到锁
     * 2. 其他线程打断（interrupt）当前线程
     */
    void lockInterruptibly() throws InterruptedException;

    /**
     * 非阻塞式获取锁
     * 获取到锁返回true否则返回false
     */
    boolean tryLock();

    /**
     * 非阻塞式获取锁
     * 在时间内不被中断的前提下获取到锁
     * @param time 阻塞时间
     * @param unit 阻塞单位
     * @return 是否获取到锁
     * @throws InterruptedException
     */
    boolean tryLock(long time, TimeUnit unit) throws InterruptedException;

    /**
     * 解锁
     */
    void unlock();

    /**
     * 返回当前锁的条件对象
     * @return
     */
    Condition newCondition();
}
