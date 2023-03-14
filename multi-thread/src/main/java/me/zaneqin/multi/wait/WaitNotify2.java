package me.zaneqin.multi.wait;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 10:12 2022/9/29
 * modifier:
 * modifyTime:
 */
public class WaitNotify2 {

    public static void main(String[] args) {
        Object o = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (o) {
                System.out.println("线程1 获取到监视器锁");
                try {
                    o.wait();
                    System.out.println("线程1 恢复啦 我为什么这么久才恢复，因为notify方法虽然早就发生了，可是我还要获取锁才能继续执行。");
                } catch (InterruptedException e) {
                    System.out.println("线程1 wait抛出interruptedException， 即时是异常我也得获取到监视器锁才能抛出");
                }
            }
        }, "线程1");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                System.out.println("线程2 获取到监视器锁");
                System.out.println("线程2 设置线程1 中断");
                thread1.interrupt();
                System.out.println("线程2 执行完线程1中断，先睡3秒");

                try {
                    Thread.sleep(3000);
                    System.out.println("线程2 休息完了。注意了，调sleep方法和wait方法不一样，不会释放监视器锁");
                } catch (InterruptedException e) {
                    System.out.println("线程2 sleep抛出interruptedException");
                }
                System.out.println("线程2 休息够了，结束操作");
            }
        });
        thread2.start();
    }
}
