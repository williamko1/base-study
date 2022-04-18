package me.zaneqin.multi.practice.c14;

/**
 * desc : 通过轮询与休眠来实现简单的阻塞
 * 通过中断机制来取消
 * @author Zane Qin
 * creatTime : 10:25 2022/4/13
 * modifier:
 * modifyTime:
 */
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    private static final long SLEEP_GRANULARITY = 100L;

    protected SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if ((!isEmpty())) {
                    return doTake();
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
