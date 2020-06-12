package me.qinzc.array;

/**
 * desc : #283 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 *
 * @author : Zane Qin
 * creatTime : 13:31 2020/6/12
 * modifier:
 * modifyTime:
 */
public class No283MoveZero {

    /**
     * 解法2 空间最优，操作局部优化（双指针）
     * 这种方法与上面的工作方式相同，即先满足一个需求，然后满足另一个需求。它以一种巧妙的方式做到了这一点。上述问题也可以用另一种方式描述，“将所有非 0 元素置于数组前面，保持它们的相对顺序相同”。
     * 这是双指针的方法。由变量 “cur” 表示的快速指针负责处理新元素。如果新找到的元素不是 0，我们就在最后找到的非 0 元素之后记录它。最后找到的非 0 元素的位置由慢指针 “lastnonzerofoundat” 变量表示。当我们不断发现新的非 0 元素时，我们只是在 “lastnonzerofoundat+1” 第个索引处覆盖它们。此覆盖不会导致任何数据丢失，因为我们已经处理了其中的内容（如果它是非 0 的，则它现在已经写入了相应的索引，或者如果它是 0，则稍后将进行处理）。
     * 在 “cur” 索引到达数组的末尾之后，我们现在知道所有非 0 元素都已按原始顺序移动到数组的开头。现在是时候满足其他要求了，“将所有 0 移动到末尾”。我们现在只需要在 “lastnonzerofoundat” 索引之后用 0 填充所有索引。
     * C++
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)。但是，操作仍然是局部优化的。代码执行的总操作（数组写入）为 nn（元素总数）。
     * 空间复杂度：O(1)O(1)，只使用常量空间。
     */
    public void moveZeroes(int[] nums) {
        int lastNonZeroIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroIndex++] = nums[i];
            }
        }
        for (int i = lastNonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 方法三：最优解
     * 这里参考了快速排序的思想，快速排序首先要确定一个待分割的元素做中间点x，然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边。
     * 这里我们可以用0当做这个中间点，把不等于0(注意题目没说不能有负数)的放到中间点的左边，等于0的放到其右边。
     * 这的中间点就是0本身，所以实现起来比快速排序简单很多，我们使用两个指针i和j，只要nums[i]!=0，我们就交换nums[i]和nums[j]
     * <p>
     * 时间复杂度：O(n)O(n)。但是，操作是最优的。代码执行的总操作（数组写入）是非 0 元素的数量。这比上一个解决方案的复杂性（当大多数元素为 0 时）要好得多。但是，两种算法的最坏情况（当所有元素都为非 0 时）复杂性是相同的。
     * 空间复杂度：O(1)O(1)，只使用了常量空间。
     */

    public void moveZeros2(int[] nums) {
        int lastNonZeroIndex = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                int tmp = nums[cur];
                nums[cur] = nums[lastNonZeroIndex];
                nums[lastNonZeroIndex++] = tmp;
            }
        }
    }

    public void moveZeros3(int[] nums) {
        int lastNonZeroIndex = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                nums[lastNonZeroIndex] = nums[cur];
                if (lastNonZeroIndex != cur) {
                    nums[cur] = 0;
                }
                lastNonZeroIndex++;
            }
        }
    }
}
