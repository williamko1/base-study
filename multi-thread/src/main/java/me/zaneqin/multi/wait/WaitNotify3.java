package me.zaneqin.multi.wait;

/**
 * desc :
 * 1. 有可能发生 线程1 是正常恢复的，虽然发生了中断，它的中断状态也确实是 true，但是它没有抛出 InterruptedException，而是正常返回。
 * 此时，thread2 将得不到唤醒，一直 wait。
 *
 * 2. 有可能发生 线程1 异常中断，抛出IE。 thread2 唤醒
 * @author Zane Qin
 * creatTime : 10:19 2022/9/29
 * modifier:
 * modifyTime:
 */
public class WaitNotify3 {
    volatile int a = 0;

    public static void main(String[] args) {
        Object o = new Object();
        WaitNotify3 waitNotify3 = new WaitNotify3();
        Thread thread1 = new Thread(() -> {
            synchronized (o) {
                System.out.println("线程1 获取到监视器锁");
                try {
                    o.wait();
                    System.out.println("线程1 恢复正常");
                } catch (InterruptedException e) {
                    System.out.println("线程1 wait方法抛出了InterruptedException异常");
                }


            }
        }, "线程1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                System.out.println("线程2 获取到监视器锁");
                try {
                    o.wait();
                    System.out.println("线程2恢复了");
                } catch (InterruptedException e) {
                    System.out.println("线程2 wait方法抛出了InterruptedException异常");
                }
            }
        }, "线程2");
        thread2.start();

        // 这里让 thread1 和 thread2 先起来，然后再起后面的 thread3
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("线程3 拿到了监视器锁。");
                    System.out.println("线程3 设置线程1中断");
                    thread1.interrupt(); // 1
                    waitNotify3.a = 1; // 这行是为了禁止上下的两行中断和notify代码重排序
                    System.out.println("线程3 调用notify");
                    o.notify(); //2
                    System.out.println("线程3 调用完notify后，休息一会");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("线程3 休息够了，结束同步代码块");
                }
            }
        }, "线程3").start();
    }
}
