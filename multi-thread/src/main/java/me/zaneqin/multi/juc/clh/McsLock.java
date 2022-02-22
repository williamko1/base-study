package me.zaneqin.multi.juc.clh;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * desc :
 * MCS Spinlock 是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，
 * 直接前驱负责通知其结束自旋，从而极大地减少了不必要的处理器缓存同步的次数，降低了总线和内存的开销。
 * @author Zane Qin
 * creatTime : 13:44 2022/2/11
 * modifier:
 * modifyTime:
 */
public class McsLock {

    public static class McsNode{
        volatile McsNode next;
        volatile boolean isBlock = true;// 默认在等待锁
    }

    private volatile McsNode queue;

    private static final AtomicReferenceFieldUpdater<McsLock, McsNode> UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(McsLock.class, McsNode.class, "queue");

    public void lock(McsNode currentThread) {
        McsNode predecessor = UPDATER.getAndSet(this, currentThread);// step 1
        if (predecessor != null) {
            predecessor.next = currentThread;// step 2

            while (currentThread.isBlock) {// step 3
            }
        }else { // 只有一个线程在使用锁，没有前驱来通知它，所以得自己标记自己为非阻塞
            currentThread.isBlock = false;
        }
    }

    public void unlock(McsNode currentThread) {
        if (currentThread.isBlock) {// 锁拥有者进行释放锁才有意义
            return;
        }

        if (currentThread.next == null) {// 检查是否有人排在自己后面
            if (UPDATER.compareAndSet(this, currentThread, null)) {// step 4
                // compareAndSet返回true表示确实没有人排在自己后面
                return;
            } else {
                // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
                // 这里之所以要忙等是因为：step 1执行完后，step 2可能还没执行完
                while (currentThread.next == null) { // step 5
                }
            }
        }

        currentThread.next.isBlock = false;
        currentThread.next = null;// for GC
    }
}
