package me.zaneqin.multi;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 13:29 2022/2/8
 * modifier:
 * modifyTime:
 */
public class NotifyExample {

    private static final Object RESOURCE_A = new Object();

    public static void main(String[] args) throws Exception{
        Thread threadA = new Thread(() -> {
            synchronized (RESOURCE_A) {
                System.out.println("thread A get resource_a lock");

                try {
                    System.out.println("thread A begins to wait");
                    RESOURCE_A.wait(2000);
                    System.out.println("thread A ends wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (RESOURCE_A) {
                System.out.println("thread B get resource_a lock");

                try {
                    System.out.println("thread B begins to wait");
                    RESOURCE_A.wait();
                    System.out.println("thread B ends wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread threadC = new Thread(() -> {
            synchronized (RESOURCE_A) {
                System.out.println("thread C begin to notify");
                RESOURCE_A.notify();
            }
        });
        System.out.println("threadA " + threadA.getState());
        System.out.println("threadB " + threadB.getState());
        System.out.println("threadC " + threadC.getState());

        threadA.start();
        threadB.start();

        Thread.sleep(1000);
        System.out.println("threadA " + threadA.getState());
        System.out.println("threadB " + threadB.getState());
        threadC.start();


        System.out.println("threadC " + threadC.getState());

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println(threadA.getState());
        System.out.println(threadB.getState());
        System.out.println(threadC.getState());
        System.out.println("main over");
    }
}
