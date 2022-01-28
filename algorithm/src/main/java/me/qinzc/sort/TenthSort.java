package me.qinzc.sort;

import java.util.Arrays;

/**
 * desc : 十大排序算法
 *
 * @author Zane Qin
 * creatTime : 15:32 2022/1/6
 * modifier:
 * modifyTime:
 */
public class TenthSort {

    public static void main(String[] args) {
        int[] waitedSort = new int[]{3, 4, 2, 5, 9, 7, 2, 9, 3, 9, 0};
        System.out.println(Arrays.toString(insertionSort(waitedSort)));

    }

    /**
     * 冒泡排序
     *
     * 1. 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3. 针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     *
     * 最佳情况：T(n) = O(n) ** **最差情况：T(n) = O(n2) ** 平均情况：T(n) = O(n2)
     * 空间复杂度 = O(1)
     * #
     *
     **/
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     *
     * 1. 初始状态：无序区为R[1..n]，有序区为空；
     * 2. 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，
     *    将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * 3. n-1趟结束，数组有序化了。
     * 最佳情况：T(n) = O(n2) 最差情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
     * 空间复杂度 = O(1)
     *
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }


    /**
     * 插入排序
     * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
     * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间
     *
     * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
     * 1. 从第一个元素开始，该元素可以认为已经被排序；
     * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5. 将新元素插入到该位置后；
     * 6. 重复步骤2~5
     *
     * 最佳情况：T(n) = O(n) 最坏情况：T(n) = O(n2) 平均情况：T(n) = O(n2)
     * 空间复杂度 O(1)
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
        return array;
    }

    /**
     * 希尔排序
     * 是希尔（Donald Shell）于1959年提出的一种排序算法。希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，
     * 也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。它与插入排序的不同之处在于，它会优先比较距离较远的元素。
     *
     * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；
     * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
     *
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 2. 按增量序列个数k，对序列进行k 趟排序；
     * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
     *    仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * 最佳情况：T(n) = O(nlog2 n) 最坏情况：T(n) = O(nlog2 n) 平均情况：T(n) =O(nlog2n)
     * S(n) = O(1)
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array) {


        return array;
    }
}
