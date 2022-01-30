package me.zaneqin.multi;

/**
 * desc : 原子性解释
 * count++ 编译成汇编编程了4个语句
 *       18: getfield      #4                  // Field count:J
 *       19: lconst_1
 *       20: ladd
 *       21: putfield
 * javap -c UnsafeCounter
 * @author Zane Qin
 * creatTime : 13:34 2022/1/29
 * modifier:
 * modifyTime:
 */
public class UnsafeCounter {

    private long count;

    public void counter() {
        long start = 0;
        while (start++ < 10000) {
            count++;
        }
    }

    public static void main(String[] args) throws Exception{
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        Thread thread1 = new Thread(unsafeCounter::counter, "线程1");
        Thread thread2 = new Thread(unsafeCounter::counter, "线程2");
        new Thread(new Runnable() {
            @Override
            public void run() {
                unsafeCounter.counter();
            }
        }, "线程3");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(unsafeCounter.getCount());
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
