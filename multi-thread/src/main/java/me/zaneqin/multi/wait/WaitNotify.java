package me.zaneqin.multi.wait;

/**
 * desc :
 * 1. wait方法会自动释放锁
 * 2. notify方法后需要重新获取锁才能执行
 * 3. sleep方法执行后不会释放锁
 *
 * @author Zane Qin
 * creatTime : 16:30 2022/9/27
 * modifier:
 * modifyTime:
 */
public class WaitNotify {
    public static void main(String[] args) {
        Object o = new Object();
        new Thread(() -> {
            synchronized (o) {
                System.out.println("线程1 获得monitor锁");
                try {
                    o.wait();
                    System.out.println("线程1 恢复啦。我为什么这么久才恢复，因为notify方法虽然早就发生了，可是我还要获取锁才能继续执行。");
                } catch (InterruptedException e) {
                    System.out.println("线程1 wait方法抛出InterruptedException");
                }
            }
        }, "线程1").start();

        new Thread(() -> {
            synchronized (o) {
                System.out.println("线程2 拿到了监视器锁。为什么呢，因为线程1 在 wait 方法的时候会自动释放锁");
                System.out.println("线程2 执行 notify 操作");
                o.notify();
                System.out.println("线程2 执行完了 notify，先休息3秒再说。");
                try {
                    Thread.sleep(3000);
                    System.out.println("线程2 休息完啦。注意了，调sleep方法和wait方法不一样，不会释放监视器锁");
                } catch (InterruptedException e) {
                    System.out.println("线程2 sleep方法抛出InterruptedException");
                }
                System.out.println("线程2 休息够了，结束操作");
            }
        }, "线程2").start();
    }
}
