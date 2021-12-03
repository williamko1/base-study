package me.qinzc.jvm;

/**
 * desc : 同步锁消除
 *
 * @author : Zane Qin
 * creatTime : 17:25 2020/3/30
 * modifier:
 * modifyTime:
 */
public class LockEliminateTest {

    // sb.append内带有synchronized
    public static String getString(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }

    public static void main(String[] args) {
        long tsStart = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            getString("lockEliminate", "test");
        }
        System.out.println("一共消耗" + (System.currentTimeMillis() -tsStart) + "ms");

    }
}
