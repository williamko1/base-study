package me.qinzc;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 13:39 2021/8/31
 * modifier:
 * modifyTime:
 */
public class ArrayUtils {
    public static void printArray(int[] src) {
        for (int i = 0; i < src.length; i++) {
            System.out.print(src[i]);
            if (i != src.length - 1) {
                System.out.print(",");
            }
        }
    }
}
