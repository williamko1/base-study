package me.qinzc.algsstandard;

import me.qinzc.ArrayUtils;

import java.util.Arrays;

/**
 * desc : 已知长度为n的线性表A采用顺序存储结构。设计一个时间复杂度为O(n),空间复杂度为O(1)的算法，该算法删除线性表中所有值为x的数据元素。
 *
 * @author Zane Qin
 * creatTime : 13:10 2021/8/31
 * modifier:
 * modifyTime:
 */
public class Lesson21 {
    public static void main(String[] args) {
        int[] src = new int[]{1, 2, 1, 2, 3, 2};
        src = resolution2(src, 2);
        ArrayUtils.printArray(src);
    }

    /**
     * 利用原来数组的空间，把删除后的数组重新存放进原来的数组空间
     * @param src 原始数组
     * @param target 需要删除的数
     */
    public static int[] resolution1(int[] src, int target) {
        int j = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] != target) {
                src[j] = src[i];
                j++;
            }
        }
        return Arrays.stream(src).limit(j).toArray();
    }

    /**
     * 用k记录顺序表A中等于x的元素个数，一边扫描A一边统计K值，将部位x的元素前移k个位置，最后修改数组长度 原来长度-k
     * @param src
     * @param target
     * @return
     */
    public static int[] resolution2(int[] src, int target) {
        int k = 0;
        for (int i = 0; i < src.length; i++) {
            if (src[i] == target) {
                k++;
            } else {
                src[i - k] = src[i];
            }
        }
        return Arrays.stream(src).limit(k).toArray();
    }
}
