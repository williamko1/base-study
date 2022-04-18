package me.zaneqin.multi.practice.c14;

/**
 * desc : 条件队列实现的有界缓存
 * 通过Object.wait Object.notifyAll 实现
 * 条件谓词: not-full(!isFull())
 * 条件谓词: not-empty(!isEmpty())
 *
 * 与使用休眠的有界缓存相比，条件队列并没有改变原来的语义。只是进行了多方面优化：
 * 1. CPU效率
 * 2. 上下文切换开销
 * 3. 响应性
 * 无法通过 "轮询与休眠"实现的功能 同样无法使用条件队列
 * @author Zane Qin
 * creatTime : 10:39 2022/4/13
 * modifier:
 * modifyTime:
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    protected BoundedBuffer(int capacity) {
        super(capacity);
    }

    // 阻塞并直到: not-full
    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        doPut(v);
        notifyAll();
    }

    // 阻塞并直到: not-empty
    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        V v = doTake();
        notifyAll();
        return v;
    }
}
