package me.qinzc.interview.sgg2.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc : volatile 可见性
 *
 * @author : Zane Qin
 * createTime : 21:43 2023/3/15
 */
public class B01_VolatileVisitable {



    public static void main(String[] args) {
        volatileVisitableTest();
        volatileAtomicTest();
    }

    /**
     * volatile不保证原子性
     */
    private static void volatileAtomicTest() {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            System.out.println(Thread.activeCount());
            Thread.yield();
        }

        System.out.println(myData.i);
        System.out.println(myData.myInteger.get());
    }

    /**
     * 验证volatile的可见性
     * 假如没有加
     */
    private static void volatileVisitableTest() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.i);
        }, "AAA").start();

        while (myData.i == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}


