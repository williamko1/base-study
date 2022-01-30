package me.zaneqin.multi.happensbefore;

/**
 * desc : happens-before
 * join
 * 如果线程 A 执⾏操作 ThreadB.join() 并成功返回, 那么线程 B 中的任意操作 happens-before 于线程 A 从 ThreadB.join() 操作成功返回，
 * 和 start 规则刚好相反，主线程 A 等待⼦线程 B 完成，当⼦线程 B 完成后，主线程能够看到⼦线程 B 的赋值操作
 *
 * @author Zane Qin
 * creatTime : 14:53 2022/1/29
 * modifier:
 * modifyTime:
 */
public class JoinExample {

    private int x = 0;
    private int y = 0;
    private boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        JoinExample joinExample = new JoinExample();

        joinExample.x = 10;
        joinExample.y = 20;
        joinExample.flag = true;

        Thread thread1 = new Thread(joinExample::writer, "线程1");
        thread1.start();
        thread1.join();

        System.out.println("x:" + joinExample.x);
        System.out.println("y:" + joinExample.y);
        System.out.println("flag:" + joinExample.flag);

        System.out.println("主线程结束");
    }

    public void writer() {
        System.out.println("x:" + x);
        System.out.println("y:" + y);
        System.out.println("flag:" + flag);

        x = 30;
        y = 40;
        flag = false;
        System.out.println("子线程结束");
    }
}
