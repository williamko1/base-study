package me.zaneqin.multi.happensbefore;

/**
 * desc : happens-before
 *  start
 * 如果线程 A 执⾏操作 ThreadB.start() (启动线程B), 那么 A 线程的 ThreadB.start() 操
 * 作 happens-before 于线程 B 中的任意操作，也就是说，主线程 A 启动⼦线程 B 后，
 * ⼦线程 B 能看到主线程在启动⼦线程 B 前的操作
 *
 * @author Zane Qin
 * creatTime : 13:53 2022/1/29
 * modifier:
 * modifyTime:
 */
public class ReorderExample {

    int x = 0;
    int y = 1;
    volatile boolean flag = false;

    public void writer() {
        // 1
        x = 42;
        // 2
        y = 50;
        // 3
        flag = true;
    }

    public void reader() {
        if (flag) {//4
            System.out.println(x);//5
            System.out.println(y);//6
        }
    }
}
