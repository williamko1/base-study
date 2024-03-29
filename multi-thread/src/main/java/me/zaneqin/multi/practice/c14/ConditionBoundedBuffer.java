package me.zaneqin.multi.practice.c14;

import me.zaneqin.multi.juc.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * desc : 使用显式条件变量的有界缓存
 *
 * @author Zane Qin
 * creatTime : 11:19 2022/4/13
 * modifier:
 * modifyTime:
 */
public class ConditionBoundedBuffer<T> {
    private final static int BUFFER_SIZE = 10;
    protected final Lock lock = new ReentrantLock();
    // 条件谓词: notFull (count < item.length)
    private final Condition notFull = lock.newCondition();
    // 条件谓词：notEmpty (count > 0)
    private final Condition notEmpty = lock.newCondition();
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    private int tail,head,count;

    // 阻塞并直到： notFull
    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[tail] = x;
            if (++tail == items.length) {
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    // 阻塞并直到: notEmpty
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            T x = items[head];
            items[head] = null;
            if (++head == items.length) {
                head = 0;
            }
            --count;
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }
}
