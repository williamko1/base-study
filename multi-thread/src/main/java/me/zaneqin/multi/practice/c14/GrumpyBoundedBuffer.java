package me.zaneqin.multi.practice.c14;

/**
 * desc : 简单的有界缓存实现。 put 和 take 都是独占锁
 * 当不满足前提条件时，邮件缓存不会执行响应的操作。
 *
 * @author Zane Qin
 * creatTime : 15:37 2022/3/31
 * modifier:
 * modifyTime:
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws Exception {
        if (isFull()) {
            throw new RuntimeException("bufferFull exception");
        }
        doPut(v);
    }

    public synchronized V take() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("buff empty exception");
        }
        return doTake();
    }

    /**
     * 忙等待或自旋等待
     * 要么自旋（浪费cpu时间片），要么休眠（错过实时获取变更的状态的机会）
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        GrumpyBoundedBuffer<String> buffer = new GrumpyBoundedBuffer<String>(10);
        while (true) {
            try {
                String item = buffer.take();
                break;
            } catch (Exception e) {
                Thread.sleep(2000);
            }
        }
    }
}
