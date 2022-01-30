package me.zaneqin.multi.happensbefore;

/**
 * desc :  happens-before
 * start
 * 如果线程A执⾏操作ThreadB.start()(启动线程B),那么A线程的ThreadB.start()操
 * 作happens-before于线程B中的任意操作，也就是说，主线程A启动⼦线程B后
 * ⼦线程B能看到主线程在启动⼦线程B前的操作
 * @author Zane Qin
 * creatTime : 14:46 2022/1/29
 * modifier:
 * modifyTime:
 */
public class StartExample {
    private int x = 0;
    private int y = 1;
    private boolean flag = false;


    public static void main(String[] args) {
        StartExample startExample = new StartExample();
        Thread thread1 = new Thread(startExample::writer);
        startExample.x = 10;
        startExample.y = 20;
        startExample.flag = true;
        thread1.start();

        System.out.println("主线程结束");
    }

    public void writer() {
        System.out.println("x:" + x);
        System.out.println("y:" + y);
        System.out.println("flag:" + flag);

    }
}
