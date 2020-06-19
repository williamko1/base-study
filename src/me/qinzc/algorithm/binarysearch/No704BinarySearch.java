package me.qinzc.algorithm.binarysearch;

/**
 * desc : 二分查找
 *
 * @author : Zane Qin
 * creatTime : 10:57 2020/6/19
 * modifier:
 * modifyTime:
 */
public class No704BinarySearch {

    public static void main(String[] args) {

    }

    /**
     * 因为我们初始化 right = nums.length - 1
     * 所以决定了我们的「搜索区间」是 [left, right]
     * 所以决定了 while (left <= right)
     * 同时也决定了 left = mid+1 和 right = mid-1
     *
     * 因为我们只需找到一个 target 的索引即可
     * 所以当 nums[mid] == target 时可以立即返回
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch_1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right + left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 因为我们初始化 right = nums.length - 1
     * 所以决定了我们的「搜索区间」是 [left, right]
     * 所以决定了 while (left <= right)
     * 同时也决定了 left = mid+1 和 right = mid-1
     *
     * 因为我们需找到 target 的最左侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧右侧边界以锁定左侧边界
     * @param nums
     * @param target
     * @return
     */
    public static int leftBound_1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左边界
                right = mid - 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 因为我们初始化 right = nums.length - 1
     * 所以决定了我们的「搜索区间」是 [left, right]
     * 所以决定了 while (left <= right)
     * 同时也决定了 left = mid+1 和 right = mid-1
     *
     * 因为我们需找到 target 的最右侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧左侧边界以锁定右侧边界
     *
     * 又因为收紧左侧边界时必须 left = mid + 1
     * 所以最后无论返回 left 还是 right，必须减一
     * @param nums
     * @param target
     * @return
     */
    public static int rightBound_1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右边界
                left = mid + 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

    /**
     * 有边界right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch_2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     *
     * 因为我们需找到 target 的最左侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧右侧边界以锁定左侧边界
     * @param nums
     * @param target
     * @return
     */
    public static int leftBound_2(int[] nums, int target) {
        int left = 0 , right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                // 锁定左边界
                right = mid;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 因为我们初始化 right = nums.length
     * 所以决定了我们的「搜索区间」是 [left, right)
     * 所以决定了 while (left < right)
     * 同时也决定了 left = mid + 1 和 right = mid
     *
     * 因为我们需找到 target 的最右侧索引
     * 所以当 nums[mid] == target 时不要立即返回
     * 而要收紧左侧边界以锁定右侧边界
     *
     * 又因为收紧左侧边界时必须 left = mid + 1
     * 所以最后无论返回 left 还是 right，必须减一
     * @param nums
     * @param target
     * @return
     */
    public static int rightBound_2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

}
