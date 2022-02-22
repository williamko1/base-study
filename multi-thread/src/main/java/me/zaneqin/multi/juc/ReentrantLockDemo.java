package me.zaneqin.multi.juc;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 13:57 2022/2/11
 * modifier:
 * modifyTime:
 */
public class ReentrantLockDemo {

    private static ReentrantLock reentrantLock = new ReentrantLock(true);

    public void createOrder() {
        System.out.println(Thread.currentThread().getName() + "刚进入createOrder");
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + "进入锁");
        try {
            System.out.println(Thread.currentThread().getName() + "在执行");
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被打断了");
        } finally {
            System.out.println(Thread.currentThread().getName() + "准备释放锁");
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            ReentrantLockDemo t = new ReentrantLockDemo();
            t.createOrder();
        }, "我是线程1");

        Thread t2 = new Thread(() -> {
            ReentrantLockDemo t = new ReentrantLockDemo();
            t.createOrder();
        }, "我是线程2");
        Thread t3 = new Thread(() -> {
            ReentrantLockDemo t = new ReentrantLockDemo();
            t.createOrder();
        }, "我是线程3");
        t1.start();
        t2.start();
        t3.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
