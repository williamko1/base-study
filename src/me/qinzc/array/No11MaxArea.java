package me.qinzc.array;

/**
 * desc : #11 盛最多的水
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * @author : Zane Qin
 * creatTime : 14:42 2020/6/12
 * modifier:
 * modifyTime:
 */
public class No11MaxArea {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea1_4(height));

    }

    /**
     * 双指针 O(n)
     * 左右指针，向中间收敛
     * @param height
     * @return
     */
    public static int maxArea1_1(int[] height) {
        int l = 0, r = height.length - 1, maxArea = 0;
        while (l < r) {
            maxArea = height[l] < height[r] ?
                    Math.max(maxArea, (r - l) * height[l++]) :
                    Math.max(maxArea, (r - l) * height[r--]);
        }
        return maxArea;
    }

    public static int maxArea1_2(int[] height) {
        int l = 0, r = height.length - 1;
        int maxArea = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - 1);
            maxArea = Math.max(area, maxArea);
            if (height[l] <= height[r]) {
                ++l;
            } else {
                --r;
            }
        }
        return maxArea;
    }

    public static int maxArea1_3(int[] height) {
        int maxArea = 0;
        for (int l = 0, r = height.length - 1; l < r; ) {
            int minHeight = height[l] < height[r] ? height[l++] : height[r--];
            int area = (r - l + 1) * minHeight;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    public static int maxArea1_4(int[] height) {
        int l = 0, r = height.length - 1, maxArea = 0;
        while (l < r) {
            int minHeight = height[l] < height[r] ? height[l++] : height[r--];
            maxArea = Math.max((r - l) * minHeight, maxArea);
        }
        return maxArea;
    }


    /**
     * 暴力枚举 n^2
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}
