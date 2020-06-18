package me.qinzc.algs4;

/**
 * desc :
 *
 * @author : Zane Qin
 * creatTime : 10:43 2020/6/18
 * modifier:
 * modifyTime:
 */
public class BinarySearch {

    public static int binarySearch(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2;
            if (key < nums[mid]) {
                high = mid - 1;
            } else if (key > nums[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
