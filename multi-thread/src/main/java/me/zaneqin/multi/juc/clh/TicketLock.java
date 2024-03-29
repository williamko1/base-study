package me.zaneqin.multi.juc.clh;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * desc : TICKET LOCK
 * Ticket Lock 是为了解决上面的公平性问题，类似于现实中银行柜台的排队叫号：
 * 锁拥有一个服务号，表示正在服务的线程，还有一个排队号；
 * 每个线程尝试获取锁之前先拿一个排队号，然后不断轮询锁的当前服务号是否是自己的排队号，如果是，则表示自己拥有了锁，不是则继续轮询。
 *
 * 当线程释放锁时，将服务号加1，这样下一个线程看到这个变化，就退出自旋。
 *
 * 缺点：
 * Ticket Lock 虽然解决了公平性的问题，但是多处理器系统上，每个进程/线程占用的处理器都在读写同一个变量serviceNum ，
 * 每次读写操作都必须在多个处理器缓存之间进行缓存同步，这会导致繁重的系统总线和内存的流量，大大降低系统整体的性能。
 *
 * 下面介绍的CLH锁和MCS锁都是为了解决这个问题的。
 *
 * MCS 来自于其发明人名字的首字母： John Mellor-Crummey和Michael Scott。
 *
 * CLH的发明人是：Craig，Landin and Hagersten。
 * @author Zane Qin
 * creatTime : 13:39 2022/2/11
 * modifier:
 * modifyTime:
 */
public class TicketLock {

    // 服务号
    private AtomicInteger serviceNum = new AtomicInteger();
    // 排队号
    private AtomicInteger orderNum = new AtomicInteger();


    public int lock() {
        // 首先原子性地获取一个排队号
        int myTicketNum = orderNum.getAndIncrement();

        // 只要当前的服务号不是自己就不断轮询
        while (serviceNum.get() != myTicketNum) {

        }
        return myTicketNum;
    }

    public void unlock(int myTicket) {
        // 只有当前线程的拥有者才能释放锁
        int next = myTicket + 1;
        serviceNum.compareAndSet(myTicket, next);
    }
}
