package me.zaneqin.multi.practice.c14;

/**
 * desc : 有界缓存实现的基类   c14-2
 *
 * 生产-消费的设计中经常会用像ArrayBlockingQueue这样的有界缓存。 put 和 take 都有一个前置条件：
 * 不能从空缓存中取数据
 * 不能往满缓存中放数据
 * @author Zane Qin
 * creatTime : 15:28 2022/3/31
 * modifier:
 * modifyTime:
 */
public abstract class BaseBoundedBuffer<V> {
    private final V[] buf;
    private int tail;
    private int head;
    private int count;

    protected BaseBoundedBuffer(int capacity) {
        this.buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length) {
            tail = 0;
        }
        ++count;
    }

    protected synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        --count;
        return v;
    }

    public synchronized final boolean isFull() {
        return count == buf.length;
    }

    public synchronized final boolean isEmpty() {
        return count == 0;
    }
}
