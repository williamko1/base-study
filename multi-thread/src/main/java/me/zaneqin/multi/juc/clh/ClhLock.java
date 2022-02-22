package me.zaneqin.multi.juc.clh;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * desc :
 * CLH锁也是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，它不断轮询前驱的状态，
 * 如果发现前驱释放了锁就结束自旋。
 * @author Zane Qin
 * creatTime : 13:51 2022/2/11
 * modifier:
 * modifyTime:
 */
public class ClhLock {
    public static class CLHNode {
        private volatile boolean isLocked = true; // 默认是在等待锁
    }

    @SuppressWarnings("unused" )
    private volatile CLHNode tail ;
    private static final AtomicReferenceFieldUpdater<ClhLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater
            . newUpdater(ClhLock.class, CLHNode .class , "tail" );

    public void lock(CLHNode currentThread) {
        CLHNode preNode = UPDATER.getAndSet( this, currentThread);
        if(preNode != null) {//已有线程占用了锁，进入自旋
            while(preNode.isLocked ) {
            }
        }
    }

    public void unlock(CLHNode currentThread) {
        // 如果队列里只有当前线程，则释放对当前线程的引用（for GC）。
        if (!UPDATER .compareAndSet(this, currentThread, null)) {
            // 还有后续线程
            currentThread. isLocked = false ;// 改变状态，让后续线程结束自旋
        }
    }
}
