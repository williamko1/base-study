package me.qinzc.jvm;

/**
 * desc : 逃逸分析
 * 未发生逃逸的对象分配到栈中，减少gc开销
 * @author : Zane Qin
 * creatTime : 17:04 2020/3/30
 * modifier:
 * modifyTime:
 */
public class EscapeAnalysisTest {

    public static void main(String[] args) {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            alloc();
        }
        long a2 = System.currentTimeMillis();
        System.out.println("cost:" + (a2 - a1) + "ms");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // 此方法内的User对象，未发生逃逸
    private static void alloc() {
        User user = new User();
    }
    static class User{

    }
}
