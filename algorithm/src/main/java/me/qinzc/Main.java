package me.qinzc;

import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
//        int[] num = new int[]{0,1,1};
//        removeDuplicates(num);
        int[] nums = new int[]{2, 4, 1};
        System.out.println(maxProfit(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }
        return j + 1;
    }

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
