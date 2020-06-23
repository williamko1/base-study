package me.qinzc.algorithm.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * desc :
 * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引左闭右开区间 [left, right) 称为一个「窗口」。
 * 2、我们先不断地增加 right 指针扩大窗口 [left, right)，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
 * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right)，
 * 直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。
 * 同时，每次增加 left，我们都要更新一轮结果。
 * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
 * 这个思路其实也不难，第 2 步相当于在寻找一个「可行解」，
 * 然后第 3 步在优化这个「可行解」，最终找到最优解，也就是最短的覆盖子串。
 * 左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动，这就是「滑动窗口」这个名字的来历。
 *
 * @author : Zane Qin
 * creatTime : 17:24 2020/6/22
 * modifier:
 * modifyTime:
 */
public class No76MinimumWindowSubstring {

    /* 滑动窗口算法框架 */
    public void slidingWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }


        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            /*** debug 输出的位置 ***/
            System.out.println(String.format("window: [%d, %d)\n", left, right));
            /********************/

            // 判断左侧窗口是否要收缩
            // window needs shrink
            while (true) {
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
            }
        }
    }
}
