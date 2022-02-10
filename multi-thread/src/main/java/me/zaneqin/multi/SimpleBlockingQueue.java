package me.zaneqin.multi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 16:31 2022/2/8
 * modifier:
 * modifyTime:
 */
public class SimpleBlockingQueue<T> {

    final Lock lock = new ReentrantLock();
    // 条件变量： 队列不满
    final Condition notFull = lock.newCondition();
    // 条件变量： 队列不空
    final Condition notEmpty = lock.newCondition();

    /** 入队 */
    void enq(T x) throws InterruptedException {
        lock.lock();
        try {
            while ("队列已满".equals("队列已满")) {
                // 等待队列不满
                notFull.await();
            }
            // 省略入队操作
            // 入队后 通知可以出队
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 出队
     */
    void deq() throws InterruptedException {
        lock.lock();
        try {
            while ("队列已空".equals("duilieyikong")) {
                // 等待队列不空
                notEmpty.await();
            }
            // 省略出队操作
            // 出队后 通知可以入队
            notFull.signal();
        }finally {
            lock.unlock();
        }

    }

}
