package me.zaneqin.multi.juc.practice;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * desc : 抽象队列同步器 AQS ， 自写
 *
 * @author Zane Qin
 * creatTime : 09:44 2022/2/24
 * modifier:
 * modifyTime:
 */
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements Serializable {
    public AbstractQueuedSynchronizer() {
    }

    /**
     * CLH 队列的节点类
     */
    static final class Node{

        /** 共享模式的标记 */
        static final Node SHARED = new Node();
        /** 独占模式的标记 */
        static final Node EXCLUSIVE = null;
        /**
         * 线程已经取消等待的状态值
         * 此节点由于超时或中断而被取消。节点永远不会离开这个状态。特别是，带有被取消节点的线程不会再阻塞
         * */
        static final int CANCELED = 1;
        /**
         * 说明后继节点的线程需要唤醒unpark
         * 这个节点的后继节点被(或即将被)阻塞(通过park)，因此当前节点在释放或取消时必须unpark它的后继节点。
         * 为了避免竞争，acquire方法必须首先表明它们需要一个信号，然后重试原子获取，然后，在失败时阻塞
         * */
        static final int SIGNAL = -1;
        /**
         * 线程正在条件队列等待的状态值
         * 此节点当前位于条件队列中。它将不会被用作一个同步队列节点，直到传输，此时状态将被设置为0。
         * (这里使用这个值与字段的其他用途无关，只是简化了机制。)
         *  */
        static final int CONDITION = -2;
        /**
         * 下一个 acquireShared 必须无条件传播
         * releaseShared应该传播到其他节点。在doReleaseShared中设置(仅用于头节点)，以确保传播继续进行，即使其他操作已经介入。
         * */
        static final int PROPAGATE = -3;

        /**
         * 节点的等待状态
         * 0:以上的值都不是按数字排列的，以简化使用。
         * 非负值意味着节点不需要发出信号。因此，大多数代码不需要检查特定的值，只需要检查符号。
         * 对于普通同步节点，该字段被初始化为0，对于条件节点，该字段被初始化为CONDITION。
         * 使用CAS(或者在可能的情况下，使用无条件volatile写操作)修改它。
         * */
        volatile int waitStatus;
        /** 前驱节点 */
        volatile Node prev;
        /** 后继节点 */
        volatile Node next;
        /** 持有的线程 */
        volatile Thread thread;
        /**
         * 单向条件队列的指针
         */
        Node nextWaiter;

        final boolean isShared(){
            return nextWaiter == SHARED;
        }

        final Node predecessor() {
            Node p = prev;
            if (p == null) {
                throw new NullPointerException();
            } else {
                return p;
            }
        }

        public Node() { // Used to establish initial head or SHARED marker
        }

        Node(Thread thread, Node mode) {     // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) { // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }

    /** 阻塞队列头节点 */
    private transient Node head;
    /** 阻塞队列尾结点 */
    private transient Node tail;
    /** 同步状态 0表示没有被占用，大于0代表有线程持有锁 这个值可以大于1， 锁可以重入， 每次重入都+1 */
    private volatile int state;

    protected final int getState() {
        return state;
    }

    protected final void setState(int newState) {
        this.state = newState;
    }

    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * 自旋比定时唤醒(unpark)快的纳秒数。粗略的估计就足以在很短的超时时间内提高响应能力。
     */
    private final Long spinForTimeoutThreshold = 1000L;

    /**
     * 为了dequeue设置队列头结点为node， 只由acquire方法调用. 为了方便GC 和 抑制不必要的信号和遍历，把未使用的字段设置成null.
     * @param node
     */
    private void setHead(Node node) {
        this.head = node;
        node.thread = null;
        node.prev = null;
    }

    //**************************** 以下五个方式是需要子类实现的方法 用于模板方法中调用 **************
    /**
     * 非阻塞式尝试以独占模式获取。这个方法应该查询对象的状态是否允许以独占模式获取它，如果允许，则获取它。
     * 这个方法总是由执行acquire的线程调用。如果这个方法报告了失败，acquire方法可能会让还没有进入队列的线程进入队列，直到其他线程发出释放的信号。
     * 这可以用来实现Lock.tryLock()方法。
     * @param args
     * @return
     */
    protected boolean tryAcquire(int args) {
        throw new UnsupportedOperationException();
    }

    /**
     * 尝试释放独占模式的锁
     */
    protected boolean tryRelease(int args) {
        throw new UnsupportedOperationException();
    }

    /**
     * 尝试获取共享模式的锁
     * @param args
     * @return
     */
    protected boolean tryAcquireShared(int args) {
        throw new UnsupportedOperationException();
    }

    /**
     * 尝试释放共享模式的锁
     */
    protected boolean tryReleaseShared(int args) {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取是否同步 是对当前（调用）线程独占的
     * @return
     */
    protected boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }
    //**************************** 以上五个方式是需要子类实现的方法 用于模板方法中调用 **************

    //**************************** 以下是模板方法 **************

    /**  */
    public final void acquire(int arg) {

    }

    public final void acquireInterruptibly(int arg) throws InterruptedException {

    }

    public final void acquireNanos(int arg, int nanos) throws InterruptedException {

    }

    public final boolean tryAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {

        return false;
    }

    public final boolean release(int arg) {


        return false;
    }

    public final void acquireShared(int arg) {

    }

    public final void acquireSharedInterruptibly(int arg) throws InterruptedException {

    }

    public final boolean tryAcquireSharedNanos(int arg) throws InterruptedException{

        return false;
    }

    public final boolean releaseShared(int arg) {

        return false;
    }



    /**
     * Setup to support compareAndSet. We need to natively implement
     * this here: For the sake of permitting future enhancements, we
     * cannot explicitly subclass AtomicInteger, which would be
     * efficient and useful otherwise. So, as the lesser of evils, we
     * natively implement using hotspot intrinsics API. And while we
     * are at it, we do the same for other CASable fields (which could
     * otherwise be done with atomic field updaters).
     */
    private static final Unsafe unsafe = getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;

    private static Unsafe getUnsafe() {

        Field f = null;
        Unsafe unsafe = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return unsafe;
    }

    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (java.util.concurrent.locks.AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (java.util.concurrent.locks.AbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (java.util.concurrent.locks.AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                    (Node.class.getDeclaredField("next"));

        } catch (Exception ex) { throw new Error(ex); }
    }
}
