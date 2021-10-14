package me.qinzc.algsstandard;

import me.qinzc.ArrayUtils;

/**
 * desc :设顺序表L有10个整数。设计一个算法，以第一个元
 * 素为分界线（基准），将所有小于等于它的元素移到该元素的前面，
 * 将所有大于它的元素移到该元素的后面。
 *
 * @author Zane Qin
 * creatTime : 13:29 2021/8/31
 * modifier:
 * modifyTime:
 */
public class Lesson22 {

    public static void main(String[] args) {
        int[] src = new int[]{3, 8, 2, 7, 1, 5, 3, 4, 6, 0};
        resolution2(src);
        ArrayUtils.printArray(src);
    }

    /**
     * pivot=L->data[0]（基准）
     * j从后向前找≤pivot的元素  两者交换
     * i从前向后找>pivot的元素
     * @param src
     */
    public static void resolution1(int[] src) {
        int i = 0, j = src.length - 1, tmp;
        // 以src[0]为基准
        int pivot = src[0];
        while (i < j) {
            while (i < j && src[j] > pivot) {
                // 从后向前扫描，找一个<= pivot 的元素 src[j]
                j--;
            }
            while (i < j && src[i] <= pivot) {
                // 从前向后扫描，找一个 > pivot 的元素 src[i]
                i++;
            }
            // 交换位置 src[i] <=> src[j]
            if (i < j) {
                tmp = src[i];
                src[i] = src[j];
                src[j] = src[tmp];
            }
        }
        // 交换位置 src[0] <=> src[j]
        tmp = src[0];
        src[0]= src[j];
        src[j] = tmp;
    }

    /**
     * pivot=src[0]（基准）
     * j从后向前找小于等于pivot的元素：前移
     * i从前向后找大于pivot的元素：后移
     * @param src
     */
    public static void resolution2(int[] src) {
        int i = 0, j = src.length - 1;
        // 以data[0]为基准
        int pivot = src[0];
        while (i < j) {
            while (i < j && src[j] > pivot) {
                // 从右向左扫描，找一个<= pivot的记录data[j]
                j--;
            }
            // 将其放入data[i]处
            src[i] = src[j];
            while (i < j && src[i] <= pivot) {
                // 从左向右扫描，找一个> pivot的记录data[i]
                i++;
            }
            // 将其放入data[j]
            src[j] = src[i];
        }
        // 放置基准
        src[i] = pivot;
    }
}
