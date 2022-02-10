package me.zaneqin.multi.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * desc : 自定义互斥锁
 *
 * @author Zane Qin
 * creatTime : 14:08 2022/2/9
 * modifier:
 * modifyTime:
 */
public class MyMutex implements Lock {

    // 聚合自定义同步器
    private final MySync sync = new MySync();

    // 自定义同步器  继承抽象队列同步器
    private static class MySync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            // 调用AQS提供的方法，保证原子性
            if (compareAndSetState(0, arg)) {
                // 我们实现的是互斥锁，所以标记获取到同步状态（更新state成功）的线程
                // 主要为了判断是否可重入
                setExclusiveOwnerThread(Thread.currentThread());
                // 获取同步状态成功，返回true
                return true;
            }
            // 获取同步状态失败，返回false
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 未拥有锁却让释放，抛IMSE
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            // 可以释放，清空排它线程标记
            setExclusiveOwnerThread(null);
            // 设置同步状态为0，表示释放锁
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 主要用于等待/通知机制，每个condition拥有一个与之对应的条件等待队列。
        Condition newCondition() {
            return new ConditionObject();
        }

    }

    @Override
    public void lock() {
        // 阻塞式的获取锁，调用同步器模板方法独占式，获取同步状态
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // 调用同步器模板方法可中断式获取同步状态
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        // 调用自己重写的方法，非阻塞式的获取同步状态
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // 调用同步器模板方法，可响应中断和超时时间限制
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        // 释放锁
        sync.release(1);

    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
