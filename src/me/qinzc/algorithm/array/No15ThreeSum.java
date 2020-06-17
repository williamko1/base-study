package me.qinzc.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * desc : #15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。

 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * @author : Zane Qin
 * creatTime : 13:29 2020/6/15
 * modifier:
 * modifyTime:
 */
public class No15ThreeSum {

    public static void main(String[] args) {
        int[] nums = new int []{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        a:
        for (int i = 0; i < nums.length - 2; i++) {

            int a = nums[i];
            b:
            for (int j = i + 1; j < nums.length - 1; j++) {
                int b = nums[j];
                c:
                for (int k = j + 1; k < nums.length; k++) {
                    List<Integer> array = new ArrayList<>();
                    int c = nums[k];
                    if (a + b + c == 0) {
                        array.add(a);
                        array.add(b);
                        array.add(c);
                        result.add(array);
                    }
                }
            }
        }
        return result;
    }
}
